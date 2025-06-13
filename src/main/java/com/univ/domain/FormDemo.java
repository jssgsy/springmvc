package com.univ.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author univ
 * @datetime 2019/1/2 7:02 PM
 * @description
 */
public class FormDemo {

    private int age;

    private Integer age2;

    private String name;

    /**
     * 非0便为真
     */
    private boolean succeded;

    private Boolean succeded2;

    private Integer[] ids;

    private List<Integer> ids2;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age2) {
        this.age2 = age2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSucceded() {
        return succeded;
    }

    public void setSucceded(boolean succeded) {
        this.succeded = succeded;
    }

    public Boolean getSucceded2() {
        return succeded2;
    }

    public void setSucceded2(Boolean succeded2) {
        this.succeded2 = succeded2;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public List<Integer> getIds2() {
        return ids2;
    }

    public void setIds2(List<Integer> ids2) {
        this.ids2 = ids2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "FormDemo{" +
                "age=" + age +
                ", age2=" + age2 +
                ", name='" + name + '\'' +
                ", succeded=" + succeded +
                ", succeded2=" + succeded2 +
                ", ids=" + Arrays.toString(ids) +
                ", ids2=" + ids2 +
                ", birthday=" + birthday +
                '}';
    }
}
