package cn.wzheart.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
}
