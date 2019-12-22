package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.WechatMiniClient;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WechatMiniClient wechatMiniClient;

    @GetMapping(value = "/test")
    @ApiOperation("测试")
    public ApiResult<String> testAddQueue(String code) throws Exception {
        WechatOpenidDTO wod = wechatMiniClient.jscode2session(code,"wx5ab86e4384eecfdd");
        User user = userService.smallAppOauth(wod);
        return ApiResult.success(JSONObject.toJSONString(wod));
    }

    @PostMapping(value = "/regist")
    @ApiOperation("测试")
    public ApiResult<User> regist(User user) throws Exception {
//        String token = null;
        return ApiResult.success(userService.regist(user));
    }

}
