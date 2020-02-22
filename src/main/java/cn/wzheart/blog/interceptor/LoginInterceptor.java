package cn.wzheart.blog.interceptor;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wz
 * @date 2020-02-22 13:44
 * 登陆拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 如果没有登录，重定向到登录页
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }

        // 有值就继续执行
        return true;
    }
}
