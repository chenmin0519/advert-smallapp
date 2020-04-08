package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.OSSClientUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {
    @Autowired
    private OSSClientUtils ossClient;

    @PostMapping(value = "/uploadFile",headers = "content-type=multipart/form-data")
    @ApiOperation("上传图片")
    public ApiResult<String> uploadFile(@RequestParam("file") @ApiParam("文件") MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        String url = ossClient.uploadFile(file.getInputStream(),"/images",file.getOriginalFilename());
        return ApiResult.success(url);
    }


    @GetMapping(value = "/geUrl")
    @ApiOperation("获取url")
    public ApiResult<String> geUrl(@RequestParam("file") @ApiParam("请求openid必须") String key) throws Exception {
        String url = ossClient.geUrl(key);
        return ApiResult.success(url);
    }


}
