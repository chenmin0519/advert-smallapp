package com.advert.smallapp.tdo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnumTdo {
    @ApiModelProperty("枚举编码")
    private String enumCode;
    @ApiModelProperty("枚举名称")
    private String enumName;
}
