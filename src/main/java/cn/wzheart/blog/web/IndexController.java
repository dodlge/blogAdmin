package cn.wzheart.blog.web;

import cn.wzheart.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        //int a = 1/0;
        if (true){
            throw new NotFoundException("找不到页面");
        }else{

        }
        return "index";
    }
}
