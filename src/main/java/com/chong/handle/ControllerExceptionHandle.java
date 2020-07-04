package com.chong.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理：不同的方法处理不同的异常，可对controller中被 @RequestMapping，@getMapping注解的方法加一些逻辑处理
 */
@ControllerAdvice
public class ControllerExceptionHandle {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)//该方法可做异常处理，异常的处理类型为exception
    public ModelAndView exceptionHandle(HttpServletRequest request,Exception e) throws Exception {
        //记录异常信息，url，exception
        logger.error("Request URL : {},Exception : {}",request.getRequestURI(),e);
        //当某些异常被标记状态码时，则不处理，例如404由notfindexception处理-->404页面
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;//若该状态码已被标记，则使用被标记的处理逻辑
        }
        ModelAndView view = new ModelAndView();
        view.addObject("url",request.getRequestURI());
        view.addObject("exception",e);
        //返回到 error异常页面
        view.setViewName("error/error");
        return view;
    }
}
