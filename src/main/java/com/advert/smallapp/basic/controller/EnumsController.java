package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.enums.ImageTypeEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/enums")
@Slf4j
public class EnumsController {

    @GetMapping(value = "/queryImageEnums")
    @ApiOperation("查询wheelplanting所有类型")
    public ApiResult<Map<String,String>> queryImageEnums(){
        Map<String,String> f = new HashMap<>();
        for(ImageTypeEnum imageTypeEnum : ImageTypeEnum.values()){
            f.put(imageTypeEnum.getKey(),imageTypeEnum.getValue());
        }
        return ApiResult.success(f);
    }

}
