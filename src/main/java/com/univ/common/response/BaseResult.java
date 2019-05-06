package com.univ.common.response;

import java.util.List;

import lombok.Data;

/**
 * @author univ
 * @date 2019/5/6 5:23 PM
 * @description
 */
@Data
public class BaseResult {
    private Integer code = 200;

    private String msg = "good";

    /**
     * @author univ
     * @date 2019/5/6 5:23 PM
     * @description
     */
    @Data
    public static class ListResult<T> extends BaseResult {
        private List<T> data;
    }

    /**
     * @author univ
     * @date 2019/5/6 5:00 PM
     * @description
     */
    @Data
    public static class PlainResult<T> extends BaseResult {

        private T data;
    }
}
