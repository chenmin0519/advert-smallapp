package com.advert.smallapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.*;

@ApiModel("用户信息")
@Table(name = "goods_detail")
@Data
public class GoodsDetail {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ApiModelProperty(value = "图集json")
    @Column(name = "images")
    public String images;

    @ApiModelProperty(value = "商品id")
    @Column(name = "goods_id")
    public Long goodsId;

    @ApiModelProperty(value = "描述")
    @Column(name = "content")
    public String content;
}
