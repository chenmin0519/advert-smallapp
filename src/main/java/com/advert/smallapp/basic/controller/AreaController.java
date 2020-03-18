package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.pojo.Area;
import com.advert.smallapp.service.AreaService;
import com.advert.smallapp.tdo.AreaAll;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/getAll")
    @ApiOperation("获取默认地址 北京")
    public ApiResult<AreaAll> getDefault() throws Exception {
        AreaAll areaAll = areaService.getDefault();
        return ApiResult.success(areaAll);
    }

    @GetMapping(value = "/getByParent")
    @ApiOperation("获取默认地址 北京")
    public ApiResult<List<Area>> getByParent(@ApiParam("父级id")@RequestParam(value = "parentId",required = true) Long parentId) throws Exception {
        List<Area> areas = areaService.getByParent(parentId);
        return ApiResult.success(areas);
    }
}
