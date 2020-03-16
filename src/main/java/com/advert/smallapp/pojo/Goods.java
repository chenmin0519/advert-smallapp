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

    @ApiModelProperty("广告位面积:平方厘米")
    @Column(name = "area")
    private Integer area;

    @ApiModelProperty("广告位类型")
    @Column(name = "category")
    private String category;

    @ApiModelProperty("广告位规格")
    @Column(name = "spec")
    private String spec;

    @ApiModelProperty("时间段")
    @Column(name = "time_period")
    private LocalDateTime timePeriod;

    @ApiModelProperty("人流量/区位价值")
    @Column(name = "traffic_was")
    private Integer trafficWas;

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
    private String is_hot;
}
