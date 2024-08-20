package com.univ.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author univ
 * date 2024/8/20
 */
@Data
public class JacksonObj {
    private String name = "univ";

    /**
     * DateTimeFormat注解：用来JacksonObj前不加@RequestBody
     * JsonFormat注解：是jackson中注解，此时JacksonObj前可加@RequestBody
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH/mm/ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH/mm/ss")
    private Date date = new Date();

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate localDate = LocalDate.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime = LocalDateTime.now();
}
