package com.univ.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author univ
 * @date 2019/1/8 10:07 AM
 * @description 演示spring mvc中@Validated的使用
 */
@Data
public class ValidationDemo {

    @NotBlank(message = "name不能为空")
    private String name;

    @Min(value = 20, message = "age最小值为20")
    private Integer age;

    /**
     * 1. 如果要嵌套验证ValidateInnerDemo中的规则，则这里必须使用@Valid注解
     * 2. 注意，这里的嵌套验证只有在validateInnerDemo不为null时才会进行，如果要验证此字段不为null则需要加上@NotNull
     */
    @Valid  // 要嵌套验证则必须有此@Valid注解(不能使用@Validated)
    @NotNull
    private ValidateInnerDemo validateInnerDemo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
