package com.univ.domain;

/**
 * @author univ
 * @datetime 2018/12/4 4:01 PM
 * @description
 */
public class Demo {
    private String name = "liuminglu";

    private Integer age = 28;

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

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
