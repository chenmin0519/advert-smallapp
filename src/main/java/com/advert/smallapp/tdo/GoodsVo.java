package com.advert.smallapp.tdo;

import com.advert.smallapp.pojo.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class GoodsVo extends Goods {
    @ApiModelProperty("联系人姓名")
    private String name;

    @ApiModelProperty("联系人性别")
    private String sex;

    @ApiModelProperty("联系人身份/职位")
    private Integer position;

    @ApiModelProperty("联系人电话号码")
    private String number;

    @ApiModelProperty("图片")
    private String images;

    @ApiModelProperty("信息")
    private String content;

    @ApiModelProperty("类型信息")
    private String typeName;
}
