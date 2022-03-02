package com.advert.smallapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("内容信息表")
@Table(name = "context")
@Data
public class Context {

    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 如果通过手机号去绑定微信 修改这个字段为公众平台的unionid openId是存储小程序openId的字段
     * 没有单独的unionId字段
     */
    @ApiModelProperty(value = "图片路径", required = true)
    @Column(name = "img")
    private String img;

    @ApiModelProperty(value = "姓名", required = true)
    @Column(name = "nick_name")
    private String nickName;

    @ApiModelProperty(value = "班级名称", required = true)
    @Column(name = "class_name")
    private String className;

    @ApiModelProperty(value = "描述", required = true)
    @Column(name = "context")
    private String context;
    @Column(name = "status")
    private Integer status;
}
