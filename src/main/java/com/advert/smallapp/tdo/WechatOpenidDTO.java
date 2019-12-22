package com.advert.smallapp.tdo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WechatOpenidDTO {
    @ApiModelProperty("授权用户唯一标识")
    private String openid;

    @ApiModelProperty("当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段")
    private String unionid;

    private String access_token;

    private String refresh_token;

    private String expires_in;

    private String session_key;

    private Long errcode;

    private String errmsg;
}
