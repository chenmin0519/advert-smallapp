package com.advert.smallapp.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class AccessTokenVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String access_token;
    private Integer expires_in;
    private Integer errcode;
    private String errmsg;
}
