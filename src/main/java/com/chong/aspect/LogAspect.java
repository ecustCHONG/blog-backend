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
 * @author
 * @decription
 * @date:2020/7/4 17:41
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.chong.web.*.*(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
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
