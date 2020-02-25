package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.service.BlogService;
import cn.wzheart.blog.service.TypeService;
import cn.wzheart.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wz
 * @date 2020-02-22 13:30
 * 博客
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @GetMapping("")
    public String index(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Model model,
                        Pageable pageable, BlogQuery query){
       /* Page<Blog> blogs = blogService.listBlog(pageable, query);
        model.addAttribute("blogs",blogs);
        // 分类
        List<Type> types = typeService.listType();
        model.addAttribute("types",null);*/
        return "admin/blogs";
    }

    /**
     * 添加页面
     * @return
     */
    @RequestMapping("/addView")
    public String search(){
        return "admin/blogs-input";
    }
}
