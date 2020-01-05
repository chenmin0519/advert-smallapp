package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.config.TokenCheck;
import com.advert.smallapp.exception.AppException;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.WechatMiniClient;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WechatMiniClient wechatMiniClient;

    @GetMapping(value = "/getoppenid")
    @ApiOperation("获取小程序的oppenid等信息")
    public ApiResult<WechatOpenidDTO> testAddQueue(String code) throws Exception {
        WechatOpenidDTO wod = wechatMiniClient.jscode2session(code,"wx5ab86e4384eecfdd");
        return ApiResult.success(wod);
    }

    @GetMapping(value = "/appauthor")
    @ApiOperation("校验用户是否注册过")
    public ApiResult<String> appAuthor(String openid) throws Exception {
        String oppenid = userService.appAuthor(openid);
        return ApiResult.success(oppenid);
    }

    @PostMapping(value = "/regist")
    @ApiOperation("注册")
    public ApiResult<String> regist(User user) throws Exception {
        return ApiResult.success(userService.regist(user));
    }

    @GetMapping(value = "/testtoken")
    @ApiOperation("c测试")
    @TokenCheck
    public ApiResult<String> test(String token){
        return ApiResult.success(null);
    }
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        now = now.plusSeconds(3);
        System.out.println(now);
        String str = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(str);
        System.out.println(LocalTime.parse(str,DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
