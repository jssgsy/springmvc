package com.univ.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.DateDemo;

/**
 * @author univ
 * @date 2019/3/26 3:16 PM
 * @description
 */
@RestController
@RequestMapping("date")
public class DateController {

    /**
     * 在不做任何处理的情况下，Date类型的字段返回到前端默认为时间戳形式
     * @return
     */
    @RequestMapping("/default")
    public DateDemo fn() {
        DateDemo dateDemo = new DateDemo();
        return dateDemo;
    }
}
