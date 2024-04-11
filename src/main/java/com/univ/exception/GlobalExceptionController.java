package com.univ.exception;

import com.univ.domain.Demo;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    // 无@RequestBody修饰的对象校验失败
    @ExceptionHandler(value = {BindException.class})
    public String catchBindException(BindException bindException) {
        System.out.println("catchBindException捕获异常");
        FieldError firstFieldError = bindException.getFieldError();
        String field = firstFieldError.getField();
        Object[] arguments = firstFieldError.getArguments();
        String defaultMessage = firstFieldError.getDefaultMessage();
        Object rejectedValue = firstFieldError.getRejectedValue();
        System.out.println("field: " + field);
        System.out.println("arguments: " + Arrays.toString(arguments));
        System.out.println("defaultMessage: " + defaultMessage);
        System.out.println("rejectedValue: " + rejectedValue);
        return "home";
    }

    /**
     * 指定入参必传（@RequestParam）却未传时抛出
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public String catchMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        System.out.println("catchMissingServletRequestParameterException捕获异常");
        String parameterType = exception.getParameterType();
        String parameterName = exception.getParameterName();
        String message = exception.getMessage();
        System.out.println("parameterType: " + parameterType);
        System.out.println("parameterName: " + parameterName);
        System.out.println("message: " + message);
        return "home";
    }
    /**
     * 有@RequestBody修饰的对象校验失败
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public String catchMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println("catchMethodArgumentNotValidException捕获异常");
        String message = exception.getMessage();
        // 一般不要用，是整个message，不易读
        // System.out.println("message: " + message);

        MethodParameter methodParameter = exception.getParameter();
        Class<?> methodParameterType = methodParameter.getParameterType();
        String methodParameterName = methodParameter.getParameterName();
        // 还能获取到反射的Parameter对象
        Parameter methodParameterReflect = methodParameter.getParameter();
        System.out.println("methodParameterType: " + methodParameterType);
        System.out.println("methodParameterName: " + methodParameterName);

        BindingResult bindingResult = exception.getBindingResult();
        // 获取第一个校验失败的字段异常信息
        FieldError firstFieldError = bindingResult.getFieldError();
        // 获取所有校验失败的字段异常信息
        List<FieldError> allFieldErrors = bindingResult.getFieldErrors();

        // 获取第一个校验失败的字段异常信息，底层就是bindingResult.getFieldError()
        exception.getFieldErrors();
        // 获取所有校验失败的字段异常信息，底层就是bindingResult.getFieldErrors()
        exception.getFieldErrors();

        String field = firstFieldError.getField();
        Object[] arguments = firstFieldError.getArguments();
        String defaultMessage = firstFieldError.getDefaultMessage();
        Object rejectedValue = firstFieldError.getRejectedValue();
        System.out.println("field: " + field);
        System.out.println("arguments: " + Arrays.toString(arguments));
        System.out.println("defaultMessage: " + defaultMessage);
        System.out.println("rejectedValue: " + rejectedValue);
        return "home";
    }


    /**
     * 有@RequestBody修饰，但入参未传，即body未传
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public String catchHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        System.out.println("catchHttpMessageNotReadableException捕获异常");
        // 此时没太多信息可获取，因为入参都没有
        String message = exception.getMessage();
        System.out.println("message: " + message);
        return "home";
    }

    /**
     * 使用bean validation校验controller入参(path variable或者request param)失败时
     * 注意：必须要声明spring的MethodValidationPostProcessor对象
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public String catchConstraintViolationException(ConstraintViolationException violationException) {
        // 注意，校验失败的异常信息不是violationException.getMessage()
        // System.out.println("ConstraintViolationException捕获异常： " + violationException.getMessage());
        
        System.out.println("catchConstraintViolationException捕获异常");
        // 校验失败的异常信息需要通过getConstraintViolations()获取
        Set<ConstraintViolation<?>> constraintViolations = violationException.getConstraintViolations();
        for (ConstraintViolation<?> violation :constraintViolations) {
            Object rootBean = violation.getRootBean();
            Path propertyPath = violation.getPropertyPath();
            String messageTemplate = violation.getMessageTemplate();
            String message = violation.getMessage();
            System.out.println("rootBean: " + rootBean);
            System.out.println("propertyPath: " + propertyPath);
            System.out.println("messageTemplate: " + messageTemplate);
            System.out.println("message: " + message);
        }
        return "home";
    }
}
