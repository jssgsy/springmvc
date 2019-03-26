package com.univ.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author univ
 * @date 2019/3/26 3:17 PM
 * @description 返回Date类型的字段
 */
@Data
public class DateDemo {

    /**
     * 默认返回时间戳
     */
    private Date defaultBirthday = new Date();

    /**
     * 以指定的字符串格式返回
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date anotherBirthday = new Date();


}
