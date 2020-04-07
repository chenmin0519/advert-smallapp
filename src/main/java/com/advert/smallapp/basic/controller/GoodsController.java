package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.tdo.AreaAll;
import com.advert.smallapp.tdo.GoodsSaveDto;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/save")
    @ApiOperation("保存")
    public ApiResult<Boolean> getDefault(@Param("参数")@RequestParam("saveDto") GoodsSaveDto saveDto) throws Exception {
        logger.info(JSONObject.toJSONString(saveDto));
        return ApiResult.success(true);
    }

}
