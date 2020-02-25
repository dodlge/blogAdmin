package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.entity.Type;
import cn.wzheart.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author wz
 * @date 2020-02-22 15:43
 * 分类
 */
@Controller
@RequestMapping("/admin/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 主页
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("")
    public String List(@PageableDefault(size = 10,page=0,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);

        return "admin/types";
    }

    /**
     * 添加的页面
     */
    @GetMapping("/addView")
    public String addView(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 添加type
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/add")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        // 数据验证
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        // 为空判断
        String name = type.getName();
        Type t = typeService.getTypeByName(name);
        if (t != null) {
            result.rejectValue("name","nameError","不能重复添加分类！");
        }

        // 保存
        typeService.saveType(type);

        if (type.getId()==null){
            attributes.addFlashAttribute("msg","添加成功~");
        }else{
            attributes.addFlashAttribute("msg","更新成功~");
        }
        return "redirect:/admin/types";
    }

    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editView(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        attributes.addFlashAttribute("msg","删除成功");
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

    /**
     * 获取所有
     */
    @RequestMapping("/listTypes")
    @ResponseBody
    public List<Type> ListTypes(){
        return typeService.getAll();
    }
}
