package com.advert.smallapp.tdo;

import com.advert.smallapp.pojo.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class GoodsSaveDto extends Goods {

    @ApiModelProperty("联系人姓名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("联系人性别")
    @Column(name = "sex")
    private String sex;

    @ApiModelProperty("联系人身份/职位")
    @Column(name = "position")
    private Integer position;

    @ApiModelProperty("联系人电话号码")
    @Column(name = "number")
    private Integer number;

    @ApiModelProperty("图片")
    @Column(name = "images")
    private List<String> images;
}
