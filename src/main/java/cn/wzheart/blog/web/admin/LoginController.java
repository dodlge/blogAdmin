package cn.wzheart.blog.web.admin;

import cn.wzheart.blog.entity.User;
import cn.wzheart.blog.service.UserService;
import cn.wzheart.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author wz
 * @date 2020-02-21 19:03
 * 登录操作
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录页面
     */
    @GetMapping("")
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 检查账号密码
     * @param userName 账号
     * @param password 密码
     * @param session
     * @param attributes 重定向可以收到的信息
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(userName, MD5Utils.code(password));
        // 判断用户账号密码
        if (null != user){
            // 不传递密码
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
