package com.chong.web;

import com.chong.MyException.NotFindException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @decription
 * @date:2020/7/4 15:49
 */
@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable("id")int id,@PathVariable("name")String name){
//        String blog = null;
//        if(blog==null)
//            throw new NotFindException("博客未找到");
        System.out.println(id+name);
        return "index";
    }
}
