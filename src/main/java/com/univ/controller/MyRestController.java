package com.univ.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.Demo;

/**
 * @author univ
 * @datetime 2018/12/14 6:50 PM
 * @description
 */

/*
@RestController与@Controller的区别
@RestController相当于其中的所有方法都被@ResponseBody所注解，此时可以不用显示书写@ResponseBody
当然，此时也没法返回视图，就只能纯粹返回json数据了
 */
@RestController
@RequestMapping("myrest")
public class MyRestController {

    /**
     * 这里不用显示的@ResponseBody，直接返回json数据
     * @return
     */
    @RequestMapping("/demos")
    public List<Demo> getDemos() {
        Demo d1 = new Demo();
        d1.setAge(10);
        d1.setName("aaa");
        Demo d2 = new Demo();
        d2.setAge(20);
        d2.setName("bbb");

        Demo d3 = new Demo();
        d3.setAge(20);
        d3.setName("ccc");

        Demo d4 = new Demo();
        d4.setAge(40);
        d4.setName("ddd");
        return Arrays.asList(d1, d2, d3, d4);
    }

    /**
     * @RequestBody:将前端以json字符串传来的数据转成对应的对象
     * @param demo
     * @return
     */
    @RequestMapping("/demo")
    public Demo saveDemo(@RequestBody Demo demo) {
        System.out.println(demo); // Demo{name='univ', age=20}

        return demo;
        // 返回数据
        /*"{\n"
                + "    \"name\": \"univ\",\n"
                + "    \"age\": 20\n"
                + "}"*/
    }
}
