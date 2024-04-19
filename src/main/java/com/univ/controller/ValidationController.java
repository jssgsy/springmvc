package com.univ.controller;

import com.univ.domain.ValidationCascadePO;
import com.univ.domain.ValidationDemo;
import com.univ.domain.ValidationSimplePO;
import com.univ.service.ValidationService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author univ
 * @date 2019/1/11 7:54 PM
 */
@RestController
@RequestMapping("/validate")
@Validated  // 只在校验path variable时与request param时需要。
public class ValidationController {

    /**
     * 引入hibernate-validator的5.2.1.Final版本是ok的，但引入6.0.10.Final版本竟然不行！
     * 一般不在这里使用入参BindingResult来接收绑定结果，而是在异常处理中进行处理
     * 如果这里有写BindingResult，则全局异常处理中不会捕获到MethodArgumentNotValidException异常
     */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    public ValidationSimplePO validObject(@Validated @RequestBody ValidationSimplePO validatedDemo, BindingResult result) {
        System.out.println(result);
        // 如果校验发生了错误，则做下面的处理
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().get(0).getDefaultMessage());
        }
        return validatedDemo;
    }

    /**
     * 1. 不使用BindingResult，则校验失败时会抛出MethodArgumentNotValidException异常，可使用全局异常捕获之
     * 2. 要启动@RequestBody校验，则被@RequestBody修饰的字段必须要被@Validated修饰，否则校验不启动，即使Controller类上有@Validated修饰也不行
     */
    @RequestMapping(value = "/v2", method = RequestMethod.POST)
    public ValidationSimplePO validObjectV2(@Validated @RequestBody ValidationSimplePO validatedDemo) {
        return validatedDemo;
    }

    /**
     * 因为@RequestBody所修饰的字段没有使用@Validated，所以此时不会启动校验
     */
    @RequestMapping(value = "/v2_1", method = RequestMethod.POST)
    public ValidationSimplePO validObjectV2_1(@RequestBody ValidationSimplePO validatedDemo) {
        return validatedDemo;
    }

    /**
     * 没有@RequestBody，虽然参数参接收到，但因没有@Validated所以校验不会启动
     * 注，这里使用GET方法也是相同效果
     */
    @RequestMapping(value = "/v2_2", method = RequestMethod.POST)
    public ValidationSimplePO validObjectV2_2(ValidationSimplePO validatedDemo) {
        return validatedDemo;
    }

    /**
     * 没有@RequestBody，但有@Validated，校验照样启动
     * 注，这里使用GET方法也是相同效果
     */
    @RequestMapping(value = "/v2_3", method = RequestMethod.POST)
    public ValidationSimplePO validObjectV2_3(@Validated ValidationSimplePO validatedDemo) {
        return validatedDemo;
    }

    // 作用于对象集合，没有@Valid表示级联嵌套，这里不生效
    @RequestMapping(value = "/v2_4", method = RequestMethod.POST)
    public List<ValidationCascadePO> validObjectV2_4(@Validated @RequestBody List<ValidationCascadePO> list) {
        return list;
    }

    // 作用于对象集合，必须结合@Valid使用，此时生效
    @RequestMapping(value = "/v2_5", method = RequestMethod.POST)
    public List<ValidationCascadePO> validObjectV2_5(@Validated @Valid @RequestBody List<ValidationCascadePO> list) {
        return list;
    }

    /**
     * 校验PathVariable参数时，须且只须在controller上使用@Validated（不用在这里使用@Valid注解）,否则即使在这里增加@Validated也没用
     * 注，校验path variable时不能使用BindingResult，在校验失败后会抛出ConstraintViolationException异常
     */
    @RequestMapping(value = "/v3/{id}", method = RequestMethod.GET)
    public Integer validPathVariable(@PathVariable("id") @Valid @Min(10) Integer id) {
        return id;
    }

    /**
     * 校验request param参数时，必须在controller上使用@Validated，这里不用@Valid也是生效的
     * 注，校验request param时不能使用BindingResult，在校验失败后会抛出ConstraintViolationException异常
     */
    @RequestMapping(value = "/v4", method = RequestMethod.GET)
    public ValidationSimplePO validRequestParam(@RequestParam("id") @Min(10) Integer id) {
        return new ValidationSimplePO();
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
