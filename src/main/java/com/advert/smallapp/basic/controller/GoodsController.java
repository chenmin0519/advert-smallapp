package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.config.TokenCheck;
import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.service.GoodsService;
import com.advert.smallapp.tdo.GoodsQuery;
import com.advert.smallapp.tdo.GoodsSaveDto;
import com.advert.smallapp.tdo.GoodsVo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoodsService goodsService;

    @TokenCheck
    @GetMapping(value = "/save")
    @ApiOperation("保存")
    public ApiResult<Boolean> getDefault(@Param("参数") GoodsSaveDto saveDto) throws Exception {
        logger.info(JSONObject.toJSONString(saveDto));
        goodsService.save(saveDto);
        return ApiResult.success(true);
    }
    @PostMapping(value = "/loadPage")
    @ApiOperation("分页查询")
    public PageInfo<Goods> loadPage(@RequestBody PageQuery<GoodsQuery> query){
        return goodsService.loadPage(query);
    }

    @GetMapping(value = "/loadById")
    @ApiOperation("分页查询")
    public ApiResult<GoodsVo> loadById(@RequestParam("id") Long id){
        return ApiResult.success(goodsService.loadById(id));
    }

}
