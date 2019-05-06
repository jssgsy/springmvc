package com.univ.common.response;

import lombok.Data;

/**
 * @author univ
 * @date 2019/5/6 5:48 PM
 * @description
 */
@Data
public class PlainResult<T> extends BaseResult {

    private T data;
}
