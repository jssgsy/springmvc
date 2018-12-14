package com.univ.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author univ
 * @datetime 2018/12/14 5:41 PM
 * @description 项目级别的异常统一处理
 */

/**
 * 注意，此bean必须能被component-scan扫描到，否则不起作用
 */
@ControllerAdvice
public class GlobalExceptionController {

    /**
     * 项目中所有抛出的RuntimeException都在这里处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public String catchRuntimeException(RuntimeException exception) {
        System.out.println("catchRuntimeException捕获异常： " + exception.getMessage());
        return "home";
    }

    /**
     * 项目中所有抛出的UnsupportedOperationException都在这里处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {UnsupportedOperationException.class})
    public String catchUnsupportedOperationException(UnsupportedOperationException exception) {
        System.out.println("catchUnsupportedOperationException捕获异常： " + exception.getMessage());
        return "home";
    }

    /**
     * 项目中所有抛出的Exception都在这里处理
     * 是最顶层的异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public String catchException(Exception exception) {
        System.out.println("catchException捕获异常： " + exception.getMessage());
        return "home";
    }
}
