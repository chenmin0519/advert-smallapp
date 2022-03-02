package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.service.ContextService;
import com.advert.smallapp.utils.WechatMiniClient;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/context")
@Slf4j
public class ContextController {

    @Autowired
    private ContextService contextService;

    @PostMapping(value = "/save")
    @ApiOperation("测试")
    public ApiResult<Boolean> testAddQueue(@RequestBody Context context) throws Exception {
        if(Objects.isNull(context.getImg()) || StringUtils.isEmpty(context.getImg())){
            ApiResult.error("图片不能为空！");
        }
        if(Objects.isNull(context.getNickName()) || StringUtils.isEmpty(context.getNickName())){
            ApiResult.error("姓名不能为空！");
        }
        if(Objects.isNull(context.getClassName()) || StringUtils.isEmpty(context.getClassName())){
            ApiResult.error("班级不能为空！");
        }
        if(Objects.isNull(context.getContext()) || StringUtils.isEmpty(context.getContext())){
            ApiResult.error("文案不能为空！");
        }
        if(context.getContext().length() > 3000){
            ApiResult.error("文案不得超过3000字符！");
        }
        try{
            contextService.save(context);
        }catch (Exception e){
            ApiResult.error(e.getMessage());
        }
        return ApiResult.success(true);
    }

    @PostMapping(value = "/upload")
    @ApiOperation("上传文件")
    public ApiResult<String> upload(@RequestBody Context context) throws Exception {
        if(Objects.isNull(context.getImg()) || StringUtils.isEmpty(context.getImg())){
            ApiResult.error("图片不能为空！");
        }
        if(Objects.isNull(context.getNickName()) || StringUtils.isEmpty(context.getNickName())){
            ApiResult.error("姓名不能为空！");
        }
        if(Objects.isNull(context.getClassName()) || StringUtils.isEmpty(context.getClassName())){
            ApiResult.error("班级不能为空！");
        }
        if(Objects.isNull(context.getContext()) || StringUtils.isEmpty(context.getContext())){
            ApiResult.error("文案不能为空！");
        }
        if(context.getContext().length() > 3000){
            ApiResult.error("文案不得超过3000字符！");
        }
        try{
            contextService.save(context);
        }catch (Exception e){
            ApiResult.error(e.getMessage());
        }
        return ApiResult.success("true");
    }
}
