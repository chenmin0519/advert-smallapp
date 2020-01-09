package com.advert.smallapp.tdo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReleaseDetails {
    @ApiModelProperty("广告位地址")
    private String address;

    @ApiModelProperty("广告位面积:平方厘米")
    private Integer area;

    @ApiModelProperty("广告位类型")
    private String goodsCategory;

    @ApiModelProperty("广告位规格")
    private String spec;

    @ApiModelProperty("时间段")
    private LocalDateTime time_period;

    @ApiModelProperty("人流量/区位价值")
    private Integer traffic_was;

    @ApiModelProperty("租金类型")
    private String rentCategory;

    @ApiModelProperty("租金金额")
    private Integer amount;

    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人性别")
    private String sex;

    @ApiModelProperty("联系人身份/职位")
    private Integer position;

    @ApiModelProperty("联系人电话号码")
    private Integer number;
}
