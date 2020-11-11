package com.advert.smallapp.basic;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.OSSClientUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OSSClientUtils ossClient;

    @PostMapping(value = "/uploadFile",headers = "content-type=multipart/form-data")
    @ApiOperation("上传图片")
    public ApiResult<String> uploadFile(@RequestParam("file") @ApiParam("文件") MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        String url = ossClient.uploadFile(file.getInputStream(),"/images",file.getOriginalFilename());
        return ApiResult.success(url);
    }

    @PostMapping(value = "/uploadImage")
    @ApiOperation("上传图片")
    public ApiResult<String> uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile = req.getFile("img");
        String url = ossClient.uploadFile(multipartFile.getInputStream(),"/images/goods",multipartFile.getOriginalFilename());
        logger.info(url);
        return ApiResult.success(url);
    }

    @PostMapping(value = "/uploadFriendsImage")
    @ApiOperation("上传图片")
    public ApiResult<String> uploadFriendsImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile = req.getFile("img");
        String url = ossClient.uploadFile(multipartFile.getInputStream(),"/images/friends",multipartFile.getOriginalFilename());
        logger.info(url);
        return ApiResult.success(url);
    }

    @GetMapping(value = "/geUrl")
    @ApiOperation("获取url")
    public ApiResult<String> geUrl(@RequestParam("file") @ApiParam("请求openid必须") String key) throws Exception {
        String url = ossClient.geUrl(key);
        return ApiResult.success(url);
    }


}
