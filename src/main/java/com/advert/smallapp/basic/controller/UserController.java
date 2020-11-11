package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.config.TokenCheck;
import com.advert.smallapp.enums.StatusEnum;
import com.advert.smallapp.enums.UserTypeEnum;
import com.advert.smallapp.exception.AppException;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.Test;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.UserInfoTdo;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.WechatMiniClient;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WechatMiniClient wechatMiniClient;

    @GetMapping(value = "/init3rd_session")
    @ApiOperation("获取小程序的oppenid等信息")
    public ApiResult<String> init3rd_session(@RequestParam("code") @ApiParam("请求openid必须") String code) throws Exception {
        WechatOpenidDTO wod = wechatMiniClient.jscode2session(code,"wx5ab86e4384eecfdd");
        Map<String,String> map = new HashMap<>();
        map.put("openid",wod.getOpenid());
        map.put("session_key",wod.getSession_key());
        String key = JSONObject.toJSONString(map);
        String rd_session = Base64.getEncoder().encodeToString(key.getBytes());
        return ApiResult.success(rd_session);
    }

    @GetMapping(value = "/getoppenid")
    @ApiOperation("获取小程序的oppenid等信息")
    public ApiResult<WechatOpenidDTO> testAddQueue(@RequestParam("code") @ApiParam("请求openid必须") String code) throws Exception {
        WechatOpenidDTO wod = wechatMiniClient.jscode2session(code,"wx5ab86e4384eecfdd");
        return ApiResult.success(wod);
    }

    @GetMapping(value = "/appauthor")
    @ApiOperation("校验用户是否注册过")
    public ApiResult<UserInfoTdo> appAuthor(@RequestParam("openid") @ApiParam("openid小程序唯一值")String openid) throws Exception {
        UserInfoTdo key = userService.appAuthor(openid);
        return ApiResult.success(key);
    }

    @GetMapping(value = "/checkPhone")
    @ApiOperation("校验手机号是否存在过")
    public ApiResult<String> checkPhone(@RequestParam("phone") @ApiParam("电话号")String phone) throws Exception {
        String status = userService.checkPhone(phone);
        return ApiResult.success(status);
    }

    @PostMapping(value = "/regist")
    @ApiOperation("小程序注册")
    public ApiResult<UserInfoTdo> regist(
            @RequestParam("userName") @ApiParam("用户名") String userName,
            @RequestParam("avatarUrl") @ApiParam("头像") String avatarUrl,
            @RequestParam("nickName") @ApiParam("昵称") String nickName,
            @RequestParam("phone") @ApiParam("电话") String phone,
            @RequestParam("pasWor") @ApiParam("密码") String pasWor,
            @RequestParam("gender") @ApiParam("性别") Integer gender,
            @RequestParam("openid") @ApiParam("openid") String openid) throws Exception {
//        JSONObject json = wechatMiniClient.decryptData(encryptedData, sessionKey, iv);
        User user = new User();
        user.setUsername(userName);
        user.setPhone(phone);
        user.setNickname(nickName);
        user.setSex(gender);
        user.setWxOpenid(openid);
        user.setAvatar(avatarUrl);
        user.setPassword(pasWor);
        user.setState(StatusEnum.YES.getKey());
        user.setUserType(UserTypeEnum.MOBILE.getKey());
        return ApiResult.success(userService.regist(user));
    }

    @GetMapping(value = "/testtoken")
    @ApiOperation("c测试")
    @TokenCheck
    public ApiResult<String> test(@RequestParam("token") @ApiParam("令牌")String token){
        return ApiResult.success(null);
    }
    @GetMapping(value = "/getUserInfoByToken")
    @ApiOperation("根据token获取用户信息")
    @TokenCheck
    public ApiResult<User> getUserInfoByToken(@RequestParam("token") @ApiParam("令牌")String token) {
        User user = userService.getUserInfoByToken(token);
        return ApiResult.success(null);
    }

    @Autowired
    Test test;
    @PostMapping(value = "/ceshizhifu")
    @ApiOperation("测试支付")
    public ApiResult<Boolean> pay(String str, String ii, HttpServletRequest request) throws Exception {
        Enumeration<String> pas = request.getParameterNames();
        while (pas.hasMoreElements()) {
            System.out.println(pas.nextElement());
        }
//        test.pay();
        return ApiResult.success(true);
    }

    @PostMapping(value = "/ceshidaifu")
    @ApiOperation("测试代付")
    public ApiResult<Boolean> ceshidaifu() throws Exception {

        test.daifu();
        return ApiResult.success(true);
    }

    @PostMapping(value = "/chaxun")
    @ApiOperation("查询")
    public ApiResult<String> chaxun(@RequestParam String tradeNo) throws Exception {
        String s = test.chaxun(tradeNo);
        return ApiResult.success(s);
    }

    @PostMapping(value = "/sms")
    @ApiOperation("查询")
    public ApiResult<String> sms(@RequestParam String mobile) throws Exception {
        String s = test.sms(mobile);
        return ApiResult.success(s);
    }

    @PostMapping(value = "/newPay")
    @ApiOperation("新支付")
    public ApiResult<String> newPay() throws Exception {
        String s = test.newPay();
        return ApiResult.success(s);
    }

    @PostMapping(value = "/linkPay")
    @ApiOperation("新支付")
    public ApiResult<String> linkPay() throws Exception {
        String s = test.linkPay();
        return ApiResult.success(s);
    }

    @PostMapping(value = "/linkPayOut")
    @ApiOperation("新代付付")
    public ApiResult<String> linkPayOut() throws Exception {
        String s = test.linkPayOut();
        return ApiResult.success(s);
    }

    @PostMapping(value = "/aliPay")
    @ApiOperation("支付宝")
    public ApiResult<String> aliPay() throws Exception {
        String s = test.aliPay();
        return ApiResult.success(s);
    }

    @PostMapping(value = "/sendMessage")
    @ApiOperation("支付宝")
    public ApiResult<String> sendMessage() throws Exception {
        String s = test.sendMessage();
        return ApiResult.success(s);
    }
}
