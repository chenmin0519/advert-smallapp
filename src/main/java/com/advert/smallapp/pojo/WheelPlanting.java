package com.advert.smallapp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("图片信息")
@Table(name = "wheel_planting")
@Data
public class WheelPlanting {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ApiModelProperty("图片路径")
    private String img;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("导航点击链接地址")
    private String url;
    @ApiModelProperty("类型")
    private String type;

}
