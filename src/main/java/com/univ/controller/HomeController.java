package com.univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author univ
 * @datetime 2018/11/19 8:05 PM
 * @description
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        System.out.println("hello. home");
        return "home";
    }

    @RequestMapping("/second")
    public String second(String name) {
        System.out.println("hello, " + name);
        return "home";
    }
}
