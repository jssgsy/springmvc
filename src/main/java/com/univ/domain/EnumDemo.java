package com.univ.domain;

import com.univ.enums.Week;

/**
 * @author univ
 * @datetime 2018/12/28 7:36 PM
 * @description
 */
public class EnumDemo {

    private String name;
    private Integer age;
    private Week week;

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

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "EnumDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", week=" + week +
                '}';
    }
}
