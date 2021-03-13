package com.lkl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘凯丽
 * @createTime 2021/2/24 16:33
 * @projectName springmvc-study
 * @className MyController.java
 * @description TODO
 */
@Controller
public class MyController {


    @RequestMapping(value = "/hello")
    public String hello(Model model){
//        封装数据
        System.out.println("条赚钱");
        model.addAttribute("msg","hello,springmvc");
        System.out.println("条赚钱222");
        return "hello";//视图解析器解析
    }
}
