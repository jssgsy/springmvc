package com.univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author univ
 * @datetime 2018/11/19 8:05 PM
 * @description
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * 此Controller中抛出的所有RuntimeException异常都会被这里捕获
     * 可在这里对此controller进行统一的异常处理；
     * 注意，此时@ExceptionHandler放在Controller中的粒度是某个控制器级别，可结合@ControllerAdvice对整个项目进行统一的异常处理
     * 如果项目级别也定义也此异常的处理，则以这里的为优先
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String catchRuntimeException(RuntimeException exception) {
        System.out.println(exception.getMessage());
        return "home";
    }

    /**
     * 此Controller中抛出的所有UnsupportedOperationException异常都会被这里捕获
     * @param exception
     * @return
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public String catchUnsupportedOperationException(UnsupportedOperationException exception) {
        System.out.println(exception.getMessage());
        return "home";
    }

    @RequestMapping("/home")
    public String home() {
        System.out.println("hello. home");
        return "home";
    }

    @RequestMapping("/runtime")
    public void throwRuntimeException() {
        // 业务逻辑处理

        // 在业务中抛出了异常
        throw new RuntimeException("HomeController: 抛出异常RuntimeException");
    }

    @RequestMapping("/unsupported")
    public void throwUnsupportedOperationException() {
        // 业务逻辑处理

        // 在业务中抛出了异常
        throw new UnsupportedOperationException("HomeController: 抛出异常UnsupportedOperationException");
    }

    @RequestMapping("/exception")
    public void throwException() throws Exception {
        // 业务逻辑处理

        // 在业务中抛出了异常
        throw new Exception("HomeController: 抛出异常Exception");
    }
}
