package com.chong.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.chong.web.*.*(..))")//对所有web包下的请求做切面
    public void pointCut(){}
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("------logger before-------");
        Object[] args = joinPoint.getArgs();
        String name =joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        RequestLog requestLog = new RequestLog(url, remoteAddr, name, args);
        logger.info("Request:{}",requestLog);
    }

    @After("pointCut()")
    public void doAfter(){
        logger.info("----------logger after-----------");
    }
    @AfterReturning(returning = "result",pointcut = "pointCut()")
    public void doAfterReturn(Object result){
        System.out.println("----- logger afterReturn-------");
        logger.info("Result:{}"+result);
    }
    private class RequestLog{
        private String url;
        private String id;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", id='" + id + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog(String url, String id, String classMethod, Object[] args) {
            this.url = url;
            this.id = id;
            this.classMethod = classMethod;
            this.args = args;

        }
    }
}
