package com.univ.common.response;

import java.util.List;

import lombok.Data;

/**
 * @author univ
 * @date 2019/5/6 5:47 PM
 * @description
 */
@Data
public class ListResult<T> extends BaseResult {
    private List<T> data;
}
