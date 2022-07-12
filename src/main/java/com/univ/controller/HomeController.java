package com.univ.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.univ.domain.Demo;

/**
 * @author univ
 * @datetime 2018/11/19 8:05 PM
 * @description
 */
@Controller
@RequestMapping("/home")
@Validated
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        System.out.println("hello. home");
        return "home";
    }

    /**
     * 使用@ResponseBody返回json格式
     * 需要引入jackson-databind包，spring-webmvc没有默认依赖
     *
     * @return
     */
    @RequestMapping("/json")
    @ResponseBody
    public List second() {
        List list = new ArrayList();

        List l2 = new ArrayList();
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        Demo d3 = new Demo();
        l2.add(d1);
        l2.add(d2);
        l2.add(d3);

        List l3 = new ArrayList();
        Demo d4 = new Demo();
        Demo d5 = new Demo();
        Demo d6 = new Demo();
        l3.add(d4);
        l3.add(d5);
        l3.add(d6);


        list.add(l2);
        list.add(l3);
        return list;
    }

    /**
     * 访问此接口跳去有form表单的页面
     * @return
     * loc
     */
    @RequestMapping("/form/page")
    public String goToFormPage(){
        System.out.println("form page");
        return "form";
    }

}
