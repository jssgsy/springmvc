package com.univ.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author univ
 * @date 2019/5/6 5:01 PM
 * @description 来用表示rest请求返回结果对应的实体
 */
@Data
@Accessors(chain = true)
public class RemoteSource {

    private Long id;

    private String realname;

    private String email;
}
