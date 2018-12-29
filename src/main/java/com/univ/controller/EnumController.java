package com.univ.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.EnumDemo;

/**
 * @author univ
 * @datetime 2018/12/28 7:34 PM
 * @description 演示spring mvc接收enum类型的字段和返回enum类型的字段
 */
@RestController
@RequestMapping("enum")
public class EnumController {

    /**
     * 接收enum类型字段的值，
     * 1. 如果enumDemo中的week字段传的是字面量(即MONDAY，TUESDAY，WEDNESDAY)时，可以正确解析成相应的enum值。
     * 2. 不能直接传对应的code值，此时没法正确解析(实际上，如果传的是对应的code值，实际上不论定义的code值为多少，spring mvc会按照定义的顺序将code值为0，1，2...，即如果传的week值为1，则实际被解析出来的值是TUESDAY(2, "周二")，任何在[0,2]之外的code值 ，则抛异常)
     * @param enumDemo
     * @return
     */
    @RequestMapping("/week")
    public EnumDemo basic(@RequestBody EnumDemo enumDemo) {
        System.out.println(enumDemo); // EnumDemo{name='univ', age=20, week=Week{code=10, desc='周一'}}
        return enumDemo;
        // 返回数据为：
        /*"{\n"
                + "    \"name\": \"univ\",\n"
                + "    \"age\": 20,\n"
                + "    \"week\": \"MONDAY\"\n"
                + "}"*/
    }



}
