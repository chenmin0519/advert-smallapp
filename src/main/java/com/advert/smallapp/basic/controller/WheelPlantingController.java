package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.pojo.WheelPlanting;
import com.advert.smallapp.service.WheelPlantingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wheelplanting")
@Slf4j
public class WheelPlantingController {

    @Autowired
    private WheelPlantingService wheelPlantingService;

    @GetMapping(value = "/navigation")
    @ApiOperation("查询导航图")
    public ApiResult<List<WheelPlanting>> quueryNavigation(){
        List<WheelPlanting> result = wheelPlantingService.queryNavigation();
        return ApiResult.success(result);
    }

    @PostMapping(value = "/saveImages")
    @ApiOperation("保存系统用图")
    public ApiResult<Boolean> saveImages(@RequestBody WheelPlanting param){
        wheelPlantingService.saveImages(param);
        return ApiResult.success(true);
    }

}
