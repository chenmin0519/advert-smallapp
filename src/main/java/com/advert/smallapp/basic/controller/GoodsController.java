package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.service.GoodsService;
import com.advert.smallapp.tdo.GoodsSaveDto;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/save")
    @ApiOperation("保存")
    public ApiResult<Boolean> getDefault(@Param("参数") GoodsSaveDto saveDto) throws Exception {
        logger.info(JSONObject.toJSONString(saveDto));
        goodsService.save(saveDto);
        return ApiResult.success(true);
    }
    @GetMapping(value = "/loadPage")
    @ApiOperation("保存")
    public PageInfo<Goods> loadPage(PageQuery<Goods> query){
        return goodsService.loadPage(query);
    }
}
