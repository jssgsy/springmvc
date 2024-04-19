package com.univ.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author univ
 * date 2024/4/19
 */
@Data
public class ValidationSimplePO {

    @NotBlank(message = "name不能为空")
    private String name;

    // 注，只有有非空时才校验，即这里是可空的
    @Min(value = 20, message = "age最小值为20")
    private Integer age;
}
