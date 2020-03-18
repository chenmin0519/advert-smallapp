package com.advert.smallapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("地区")
@Table(name = "area")
@Data
public class Area {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ApiModelProperty("地区编码")
    @Column(name = "code")
    private String code;
    @ApiModelProperty("名称")
    @Column(name = "area_name")
    private String areaName;
    @ApiModelProperty("等级 1:省份province,2:市city,3:区县district")
    @Column(name = "level")
    private Integer level;
    @ApiModelProperty("城市代码")
    @Column(name = "city_code")
    private String cityCode;
    @ApiModelProperty("经纬度坐标")
    @Column(name = "center")
    private String center;
    @ApiModelProperty("父级id")
    @Column(name = "parent_id")
    public Long parentId;

}
