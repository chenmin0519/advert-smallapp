package com.advert.smallapp.tdo;

import com.advert.smallapp.pojo.Area;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AreaAll {
    @ApiModelProperty("省")
    private List<Area> provinces;
    @ApiModelProperty("市")
    private List<Area> citys;
    @ApiModelProperty("区")
    private List<Area> areas;
}
