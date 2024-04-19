package com.univ.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author univ
 * date 2024/4/19
 */
@Data
public class ValidationCascadePO {

    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 1. 如果要嵌套验证ValidateInnerDemo中的规则，则这里必须使用@Valid注解
     * 2. 注意，这里的嵌套验证只有在validateInnerDemo不为null时才会进行，如果要验证此字段不为null则需要加上@NotNull
     */
    @Valid  // 要嵌套验证则必须有此@Valid注解(不能使用@Validated)
    @NotNull
    private ValidateCascadeInnerPO innerPO;

}
