package com.advert.smallapp.pojo;

import com.advert.smallapp.commons.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@ApiModel("广告位基本信息")
@Table(name = "goods")
@Data
public class Goods extends MainPO {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ApiModelProperty("广告位地址")
    @Column(name = "address")
    private String address;

    @ApiModelProperty("月租")
    @Column(name = "amount")
    private Long amount;

    @ApiModelProperty("广告位面积:平方厘米")
    @Column(name = "area")
    private Long area;

    @ApiModelProperty("广告位类型")
    @Column(name = "category")
    private String category;

    @ApiModelProperty("广告位规格")
    @Column(name = "spec")
    private String spec;

    @ApiModelProperty("人流量/区位价值")
    @Column(name = "traffic_was")
    private Long trafficWas;

    @ApiModelProperty("标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty("封面")
    @Column(name = "icon")
    private String icon;

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

    @ApiModelProperty("联系人id")
    @Column(name = "contact_id")
    private Long contactId;
}
