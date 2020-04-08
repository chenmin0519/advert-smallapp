package com.advert.smallapp.pojo;

import com.advert.smallapp.commons.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("联系人")
@Table(name = "contact")
@Data
public class Contact extends MainPO {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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
    private String number;
}
