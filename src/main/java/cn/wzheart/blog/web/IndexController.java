package cn.wzheart.blog.web;

import cn.wzheart.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable int id,@PathVariable String name){
        //int a = 1/0;
        /*if (true){
            throw new NotFoundException("找不到页面");
        }else{

        }*/
        System.out.println("index");
        return "index";
    }
}
