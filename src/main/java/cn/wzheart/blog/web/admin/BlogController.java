package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.entity.Blog;
import cn.wzheart.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping("")
    public String index(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Model model,
                        Pageable pageable, Blog blog){
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("blogs",blogs);
        return "admin/blogs";
    }
}
