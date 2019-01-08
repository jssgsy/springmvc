package com.univ.domain;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author univ
 * @date 2019/1/8 10:07 AM
 * @description 演示spring mvc中@Validated的使用
 */
public class ValidatedDemo {

    @NotBlank
    private String name;

    @Min(20)
    private Integer age;

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
