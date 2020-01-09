package com.advert.smallapp.pojo;

import com.advert.smallapp.commons.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
@ApiModel("租金")
@Table(name = "rent")
@Data
public class Rent extends MainPO {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ApiModelProperty("租金类型")
    @Column(name = "category")
    private String category;

    @ApiModelProperty("租金金额")
    @Column(name = "amount")
    private Integer amount;
}
