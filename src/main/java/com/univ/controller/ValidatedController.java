package com.univ.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.ValidationDemo;

/**
 * @author univ
 * @date 2019/1/11 7:54 PM
 * @description
 */
@RestController
@RequestMapping("/validate")
public class ValidatedController {

    /**
     * 引入hibernate-validator的5.2.1.Final版本是ok的，但引入6.0.10.Final版本竟然不行！
     * 一个@Validated必须对应一个BindingResult
     * @param validatedDemo
     * @param result
     * @return
     */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public ValidationDemo v1(@Validated @RequestBody ValidationDemo validatedDemo, BindingResult result) {
        System.out.println(result);
        // 如果校验发生了错误，则做下面的处理
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().get(0).getDefaultMessage());
        }
        return validatedDemo;
    }


    /*
    注意，不能将@Validated用于基本类型的参数上(也没有必要)，BindingResult必须紧接在被@RequestBody或者@RequestPart修饰的参数后
     */
    /*@RequestMapping(value = "/parameter", method = RequestMethod.GET)
    public String validateParameter(@Valid Long id, BindingResult result) {
        return "failed";
    }*/

    /**
     * 不使用BindingResult，则校验失败时会抛出MethodArgumentNotValidException异常，可使用全局异常捕获之
     * @param validatedDemo
     * @return
     */
    @RequestMapping(value = "/v2", method = RequestMethod.POST)
    public ValidationDemo v2(@Validated @RequestBody ValidationDemo validatedDemo) {
        return validatedDemo;
    }



}
