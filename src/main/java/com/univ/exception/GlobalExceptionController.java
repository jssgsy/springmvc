package com.univ.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.univ.domain.Demo;

/**
 * @author univ
 * @datetime 2018/12/14 5:41 PM
 * @description 项目级别的异常统一处理
 */

/**
 * 注意，此bean必须能被component-scan扫描到，否则不起作用
 */
@ControllerAdvice
@Controller
public class GlobalExceptionController {

    /**
     * 项目中所有抛出的UnsupportedOperationException及其子异常都在这里处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {UnsupportedOperationException.class})
    public String catchUnsupportedOperationException(UnsupportedOperationException exception) {
        System.out.println("catchUnsupportedOperationException捕获异常： " + exception.getMessage());
        return "home";
    }

    /**
     * 项目中所有抛出的RuntimeException及其子异常都在这里处理
     * 可以直接使之成为一个controller(使用@Controller修饰)，并返回json数据
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    public Demo catchRuntimeException(RuntimeException exception) {
        Demo d = new Demo();
        d.setAge(20);
        d.setName("捕获到的异常是：" + exception.getMessage());
        return d;
    }

    /**
     * 项目中所有抛出的Exception其子异常都在这里处理
     * 最顶层的异常处理，前面都没捕获到则这里一定会被捕获
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public String catchException(Exception exception) {
        System.out.println("catchException捕获异常： " + exception.getMessage());
        return "home";
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public String catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println("参数校验失败异常： " + exception.getBindingResult().getFieldError().getDefaultMessage());
        return "home";
    }
}
