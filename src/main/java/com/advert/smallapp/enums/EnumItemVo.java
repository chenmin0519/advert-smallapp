package com.advert.smallapp.enums;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Data
public class EnumItemVo implements Serializable {
    @ApiModelProperty("枚举编码")
    private String enumCode;
    @ApiModelProperty("枚举名称")
    private String enumName;
    @ApiModelProperty("枚举值")
    private Map<Object, Object> enumValues = new TreeMap<Object, Object>();
}

