package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.entity.Type;
import cn.wzheart.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/types")
    public String List(Pageable pageable,
                       Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);

        return "admin/types";
    }

}
