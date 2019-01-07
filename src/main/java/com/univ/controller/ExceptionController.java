package com.univ.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ
 * @datetime 2019/1/7 10:27 AM
 * @description
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    /**
     * 此Controller中抛出的所有 UnsupportedOperationException异常及其子异常 都会被这里捕获
     * 和异常处理原则一样，尽量抛具体的异常类型，不要抛顶层的异常
     *
     * 可在这里对此controller进行统一的异常处理；
     * 注意，此时@ExceptionHandler放在Controller中的粒度是某个控制器级别，可结合@ControllerAdvice对整个项目进行统一的异常处理
     * 如果项目级别也定义也此异常的处理，则以这里的为优先
     * @param exception
     * @return
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public String catchUnsupportedOperationException(UnsupportedOperationException exception) {
        System.out.println(exception.getMessage());
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
