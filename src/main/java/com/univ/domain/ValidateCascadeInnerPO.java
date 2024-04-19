package com.univ.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author univ
 * @date 2019/10/31 5:00 PM
 * @description
 */
@Data
public class ValidateCascadeInnerPO {

    @Min(value = 10, message = "id最小值为10")
    private Integer id;

    @NotBlank(message = "name不能为空")
    private String name;
}
