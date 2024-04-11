package com.univ.controller;

import com.univ.domain.ValidationDemo;
import com.univ.service.ValidationService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author univ
 * @date 2019/1/11 7:54 PM
 * @description
 */
@RestController
@RequestMapping("/validate")
@Validated  // 只在校验path variable时与request param时需要。
public class ValidationController {

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
     * 1. 不使用BindingResult，则校验失败时会抛出MethodArgumentNotValidException异常，可使用全局异常捕获之
     * 2. 要启动@RequestBody校验，则被@RequestBody修饰的字段必须要被@Validated修饰，否则校验不启动，即使Controller类上有@Validated修饰
     * @param validatedDemo
     * @return
     */
    @RequestMapping(value = "/v2", method = RequestMethod.POST)
    public ValidationDemo validObjectV2(@Validated @RequestBody ValidationDemo validatedDemo) {
        return validatedDemo;
    }

    /**
     * 因为@RequestBody所修饰的字段没有使用@Validated，所以此时不会启动校验
     */
    @RequestMapping(value = "/v2_1", method = RequestMethod.POST)
    public ValidationDemo validObjectV2_1(@RequestBody ValidationDemo validatedDemo) {
        return validatedDemo;
    }

    /**
     * 没有@RequestBody，虽然参数参接收到，但因没有@Validated所以校验不会启动
     * 注，这里使用GET方法也是相同效果
     */
    @RequestMapping(value = "/v2_2", method = RequestMethod.POST)
    public ValidationDemo validObjectV2_2(@Validated ValidationDemo validatedDemo) {
        return validatedDemo;
    }

    /**
     * 没有@RequestBody，但有@Validated，校验照样启动
     * 注，这里使用GET方法也是相同效果
     */
    @RequestMapping(value = "/v2_3", method = RequestMethod.POST)
    public ValidationDemo validObjectV2_3(@Validated ValidationDemo validatedDemo) {
        return validatedDemo;
    }

    /**
     * 校验PathVariable参数时，必须在controller上使用@Validated,否则即使在这里增加@Validated也没用
     * 注，校验path variable时不能使用BindingResult，在校验失败后会抛出ConstraintViolationException异常
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
    // -----------------------------------------

    @Resource
    private ValidationService validationService;
    @RequestMapping(value = "/service/validParamObject", method = RequestMethod.GET)
    public ValidationDemo validParamObject() {
        ValidationDemo demo = new ValidationDemo();
        // 校验service层方法入参为对象类型
        validationService.validParamObject(demo);
        return demo;
    }
    @RequestMapping(value = "/service/validParamPrimitive", method = RequestMethod.GET)
    public ValidationDemo validParamPrimitive() {
        // 校验service层方法入参为基本类型
        validationService.validParamPrimitive("", 1);

        return new ValidationDemo();
    }

    @RequestMapping(value = "/service/validReturnValud", method = RequestMethod.GET)
    public ValidationDemo validReturnValud() {
        ValidationDemo demo = new ValidationDemo();
        // 校验service层方法返回值
        validationService.validReturnValud();
        return new ValidationDemo();
    }


}
