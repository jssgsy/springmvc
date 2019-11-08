package com.univ.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import com.univ.domain.ValidationDemo;

/**
 * @author univ
 * @date 2019/11/8 8:05 PM
 * @description
 */
@Validated  // 用来进行方法(入参，返回值)验证
public interface ValidationService {

    // 入参为对象
    void validParamObject(@Valid ValidationDemo validationDemo);

    // 入参为基本类型
    void validParamPrimitive(@NotBlank(message = "ValidationService#name不能为空") String name, @Min(value = 18, message = "ValidationService#age最小值为18") int age);

    // 校验返回值
    @Min(value = 20, message = "ValidationService#validReturnValud，返回值最小为20")
    int validReturnValud();
}
