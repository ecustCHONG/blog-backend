package com.chong.web;

import com.chong.MyException.NotFindException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回首页控制器
 */
@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
//    @GetMapping("/")
    public String index(@PathVariable("id") int id, @PathVariable("name") String name) {
//        public String index(){
//        String blog = null;
//        if(blog==null)
//            throw new NotFindException("博客未找到");
//        int id2=9/0; 异常处理
        System.out.println(id + name);
        return "index";
    }
}
