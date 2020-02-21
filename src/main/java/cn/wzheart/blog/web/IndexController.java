package cn.wzheart.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    /*@GetMapping("/{view}")
    public String indexView(@PathVariable String view){
        return view;
    }*/

    @GetMapping("/error/{view}")
    public String inde404x(@PathVariable String view){
        return "error/"+view;
    }
}
