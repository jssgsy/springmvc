package com.univ.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author univ
 * @date 2019/5/6 5:01 PM
 * @description 来用表示rest请求返回结果对应的实体
 */
@Data
@Accessors(chain = true)
public class RemoteDest {

    private Long id;

    private String realNAME;

    @JsonProperty("email")
    private String emAIL;

    private String address;

    /**
     * 序列化时，以realName代替realNAME输出
     * @return
     */
    @JsonProperty("realName")
    public String getRealNAME() {
        return realNAME;
    }

    /**
     * 反序列化时，将realname的值注入到realNAME中
     * @param realNAME
     */
    @JsonProperty("realname")
    public void setRealNAME(String realNAME) {
        this.realNAME = realNAME;
    }


}
