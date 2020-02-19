package cn.wzheart.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wz
 * @date 2020-02-19 21:09
 * 统一异常处理类
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 异常处理的方法
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        // 记录日志
        logger.error("Requset URL : {},Exception  : {}",request.getRequestURI(),e);

        // 判断异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }


        // 创建返回对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("URL",request.getRequestURL());
        mv.addObject("EXCEPTION",e);
        // 返回那个页面
        mv.setViewName("error/error");
        return mv;
    }
}
