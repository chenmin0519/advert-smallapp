package com.advert.smallapp.tdo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
@ApiModel("用户信息小程序对应")
@Data
public class WechatUserRegistTdo {
    @ApiModelProperty(value = "用户名", required = true)
    @Column(name = "username")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    @Column(name = "nickname")
    private String nickname;

    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value = "性别 1-男 2女 0-未知")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty("微信openId")
    private String wxOpenid;

    @ApiModelProperty(value = "电子邮件")
    @Column(name = "email")
    private String email;
}
