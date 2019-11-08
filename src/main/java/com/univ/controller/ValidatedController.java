package com.univ.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univ.domain.ValidationDemo;

/**
 * @author univ
 * @date 2019/1/11 7:54 PM
 * @description
 */
@RestController
@RequestMapping("/validate")
@Validated  // 只在校验path variable时与request param时需要。
public class ValidatedController {

    /**
     * 引入hibernate-validator的5.2.1.Final版本是ok的，但引入6.0.10.Final版本竟然不行！
     * 使用spring的@Validated或者原生的@Valid即可。一个@Validated(@Valid)必须对应一个BindingResult
     * @param validatedDemo
     * @param result
     * @return
     */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public ValidationDemo validObject(@Validated @RequestBody ValidationDemo validatedDemo, BindingResult result) {
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
    public ValidationDemo validObjectV2(@Validated @RequestBody ValidationDemo validatedDemo) {
        return validatedDemo;
    }

    /**
     * 校验PathVariable参数时，必须在controller上使用@Validated
     * 注，校验path variable时不能使用BindingResult，在校验失败后会抛出ConstraintViolationException异常
     * @param id
     * @return
     */
    @RequestMapping(value = "/v3/{id}", method = RequestMethod.GET)
    public ValidationDemo validPathVariable(@PathVariable("id") @Valid @Min(10) Integer id) {
        return new ValidationDemo();
    }

    /**
     * 校验request param参数时，必须在controller上使用@Validated
     * 注，校验request param时不能使用BindingResult，在校验失败后会抛出ConstraintViolationException异常
     * @param id
     * @return
     */
    @RequestMapping(value = "/v4", method = RequestMethod.GET)
    public ValidationDemo validRequestParam(@RequestParam("id") @Min(10) Integer id) {
        return new ValidationDemo();
    }

}
