package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.entity.Type;
import cn.wzheart.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wz
 * @date 2020-02-22 15:43
 * 分类
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 主页
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String List(@PageableDefault(size = 3,page=0,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);

        return "admin/types";
    }

    /**
     * 添加的页面
     */
    @GetMapping("/types/addView")
    public String addView(){
        return "admin/types-input";
    }

    @PostMapping("/types/add")
    public String addType(Type type){
        typeService.saveType(type);
        return "redirect:/admin/types";
    }
}
