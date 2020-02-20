package cn.wzheart.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wz
 * @date 2020-02-20 13:50
 */
@Aspect// 切面
@Component// 开启扫描
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 切面
     */
    @Pointcut("execution(* cn.wzheart.blog.web.*.*(..))")//规定切面拦截那些类  web文件夹下面的所有方法
    public void log(){

    }

    /**
     * 前置
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取ip和url
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        // 获取方法和参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequsetLog requsetLog = new RequsetLog(url, ip, classMethod, args);

        logger.info("request : {}",requsetLog);
    }

    /**
     * 后置
     */
    @After("log()")
    public void doAfter(){
        //logger.info("-----doAfter----");
    }

    /**
     * 返回结果
     * @param res
     */
    @AfterReturning(returning = "res",pointcut = "log()")
    public void doAfterReturn(Object res){
        logger.info("result : {}"+res);
    }



    private class RequsetLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequsetLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
