package com.univ.exception;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
        d.setName("catchRuntimeException捕获异常：" + exception.getMessage());
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

    /**
     * 使用bean validation校验controller的入参(类型为Object)失败时
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public String catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println("catchMethodArgumentNotValidException捕获异常： " + exception.getBindingResult().getFieldError().getDefaultMessage());
        return "home";
    }

    /**
     * 使用bean validation校验controller入参(path variable或者request param)失败时
     * 注意：必须要声明spring的MethodValidationPostProcessor对象
     * @param violationException
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public String catchConstraintViolationException(ConstraintViolationException violationException) {
        // 注意，校验失败的异常信息不是violationException.getMessage()
        // System.out.println("ConstraintViolationException捕获异常： " + violationException.getMessage());
        
        System.out.println("catchConstraintViolationException捕获异常如下：");
        // 校验失败的异常信息需要通过getConstraintViolations()获取
        Set<ConstraintViolation<?>> constraintViolations = violationException.getConstraintViolations();
        for (ConstraintViolation violation :constraintViolations) {
            System.out.println(violation.getMessage());
        }
        return "home";
    }
}
