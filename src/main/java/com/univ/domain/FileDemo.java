package com.univ.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author univ
 * @datetime 2018/12/29 2:58 PM
 * @description 模拟前端的文件上传，同时还传其它参数
 */
public class FileDemo {

    private MultipartFile file;

    private String name;

    private Integer age;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

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
