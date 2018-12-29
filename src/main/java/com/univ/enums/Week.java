package com.univ.enums;

/**
 * @author univ
 * @datetime 2018/12/28 7:29 PM
 * @description
 */
public enum Week {
    MONDAY(10, "周一"), TUESDAY(20, "周二"), WEDNESDAY(3, "周三");

    Week(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    @Override
    public String toString() {
        return "Week{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static Week getByCode(Integer code) {
        for (Week day : Week.values()) {
            if (day.code.equals(code)) {
                return day;
            }
        }
        return null;
    }
}
