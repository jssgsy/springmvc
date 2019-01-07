package com.univ.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.FormDemo;

/**
 * @author univ
 * @datetime 2019/1/2 7:00 PM
 * @description 演示spring mvc将表单字段映射成各种类型
 */
@RestController
@RequestMapping("/form")
public class FormController {

    @RequestMapping("/basic")
    public String form(@RequestBody FormDemo formDemo) {
        System.out.println(formDemo);
        // 结果如下:
        /*"{\n"
                + "    \"age\":1,\n"
                + "    \"age2\":2,\n"
                + "    \"name\":\"univ\",\n"
                + "    \"succeded\":true,\n"
                + "    \"succeded2\":true,\n"
                + "    \"ids\":[\n"
                + "        1,\n"
                + "        2,\n"
                + "        3,\n"
                + "        4,\n"
                + "        5\n"
                + "    ],\n"
                + "    \"ids2\":[\n"
                + "        9,\n"
                + "        8,\n"
                + "        7,\n"
                + "        6\n"
                + "    ],\n"
                + "    \"birthday\":\"2019-01-02 19:01:23\"\n"
                + "}"*/
        return "ok";
    }
}
