package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.service.ContextService;
import com.advert.smallapp.utils.WechatMiniClient;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;


@RestController
@RequestMapping("/context")
@Slf4j
public class ContextController {

    @Autowired
    private ContextService contextService;

    @GetMapping(value = "/page")
    @ApiOperation("测试")
    public ApiResult<Boolean> page() throws Exception {

        return ApiResult.success(true);
    }

    @PostMapping(value = "/save")
    @ApiOperation("保存")
    public ApiResult<Boolean> save(@RequestBody Context context) throws Exception {
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


    @PostMapping(value = "/uploadFile")
    @ApiOperation("上传图片")
    public ApiResult<String> uploadFile(@RequestParam("file") @ApiParam("文件") MultipartFile file) throws Exception {
        String url = "http://yixiaoshui.top/image/";
        String name = file.getOriginalFilename();
        name = LocalDateTime.now().toString().replace(".","") + name;
        url += name;
        String filePath = "/data/www/image/" + name;
        FileOutputStream out = null;
        InputStream in = null;
        try {
            //创建File对象
            File imageFile = new File(filePath);
            //创建文件
            imageFile.createNewFile();
            //获取上传文件流
            in = file.getInputStream();
//                        使用 FileOutputStream打开服务器端的上传文件
            out = new FileOutputStream(imageFile);
//        流的对拷
            byte[] bytes = new byte[1024];//每次读取一个字节
            int len;
//          开始读取上传文件的字节，并将其输出到服务器端的上传文件输出流中
            while ((len = in.read(bytes)) > 0)
                out.write(bytes, 0, len);
        }catch (Exception e){
            in.close();
            out.close();
            ApiResult.error("上传失败！");
        }
        return ApiResult.success(url);
    }

}
