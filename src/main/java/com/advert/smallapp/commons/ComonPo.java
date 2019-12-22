package com.advert.smallapp.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Create By chenmin on 2017/12/8 11:28
 */
@Data
public class ComonPo extends BasePO {

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private Long creator;

    @JsonIgnore
    @ApiModelProperty("最后修改时间")
    private LocalDateTime editTime;

    @JsonIgnore
    @ApiModelProperty("最后修改人")
    private Long editor;
}
