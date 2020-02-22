package cn.wzheart.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author wz
 * @date 2020-02-22 13:48
 * 配置类
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * 路径处理
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截 admin 下面所有的
        // 需要放行登录页 和 账号密码验证，还有css jq
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

    /**
     * 映射静态资源文件
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    // https://blog.csdn.net/thekey1314/article/details/80863372
    // springboot 2.0无法注入
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 注册Spring data jpa pageable的参数分解器
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }
}
