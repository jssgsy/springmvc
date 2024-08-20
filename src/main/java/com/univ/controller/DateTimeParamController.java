package com.univ.controller;

import com.univ.domain.JacksonObj;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 演示controller方法如何使用接收时间类型的参数
 * @author univ
 * date 2024/8/20
 */
@RestController
@RequestMapping("/date/param")
public class DateTimeParamController {

    @GetMapping("/simple/d1")
    public JacksonObj f1(JacksonObj jacksonObj) {
        System.out.println(jacksonObj);
        return jacksonObj;
    }

    @PostMapping("/simple/d2")
    public JacksonObj f2(JacksonObj jacksonObj) {
        System.out.println(jacksonObj);
        return jacksonObj;
    }

    @PostMapping("/simple/d3")
    public JacksonObj f3(@RequestBody JacksonObj jacksonObj) {
        System.out.println(jacksonObj);
        return jacksonObj;
    }

    /**
     * 因为Date也是一个对象，因此这里一般结合@RequestParam使用，见{@link #f5(Date)}
     * @param date
     * @return
     */
    @GetMapping("/simple/d4")
    public Date f4(@DateTimeFormat(pattern = "yyyy-MM-dd HH/mm/ss") Date date) {
        System.out.println(date);
        return date;
    }

    @GetMapping("/simple/d5")
    public Date f5(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH/mm/ss") Date date) {
        System.out.println(date);
        return date;
    }

    @GetMapping("/simple/d6")
    public Date f6(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH/mm/ss") Date date,
                   @RequestParam("dateTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH/mm/ss") LocalDateTime dateTime) {
        System.out.println(date);
        System.out.println(dateTime);
        return date;
    }

}
