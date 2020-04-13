package com.advert.smallapp.tdo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class GoodsQuery {
    @ApiModelProperty("广告位类型")
    @Column(name = "category")
    private Integer category;

    @ApiModelProperty("是否推荐 1：是 2否")
    @Column(name = "is_recommend")
    private String isRecommend;

    @ApiModelProperty("是否热门 1：是 2否")
    @Column(name = "is_hot")
    private String isHot;

    @ApiModelProperty("省市县")
    @Column(name = "area_info")
    private String areaInfo;

    @ApiModelProperty("地区编码")
    @Column(name = "area_code")
    private String areaCode;
}
