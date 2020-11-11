package com.advert.smallapp.service.impl;

import com.advert.smallapp.config.Constans;
import com.advert.smallapp.exception.AppException;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.mapper.UserMapper;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.UserInfoTdo;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.RedisClient;
import com.advert.smallapp.utils.SysUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisClient redisClient;

    @Override
    public String login(String phone, String password) throws AppException {
        User query = new User();
        query.setPhone(phone);
        query.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
        User result = userMapper.selectOne(query);
        return aouthToken(result);
    }

    @Override
    public UserInfoTdo regist(User user) throws Exception {
        UserInfoTdo userInfoTdo = new UserInfoTdo();
        validataUserInfo(user);
        if(user.getPassword() != null){
            user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        }
        User query = new User();
        query.setWxOpenid(user.getWxOpenid());
        User result = userMapper.selectOne(query);
        if(result != null){
            BeanUtils.copyProperties(user,userInfoTdo);
            userInfoTdo.setToken(aouthToken(result));
            return userInfoTdo;
        }
        userMapper.insertSelective(user);
        BeanUtils.copyProperties(user,userInfoTdo);
        userInfoTdo.setToken(aouthToken(user));
        return userInfoTdo;
    }

    /**
     * 校验user是否满足条件
     * @param user
     */
    private void validataUserInfo(User user) throws AppException {
        if(StringUtils.isBlank(user.getWxOpenid())){
            ExceptionUtil.throwException(ExceptionUtil.UN_REGIST,"用户openId不能为空");
        }
        if(StringUtils.isBlank(user.getPhone())){
            ExceptionUtil.throwException(ExceptionUtil.UN_REGIST,"用户手机号不能为空");
        }
    }

    @Override
    public String checkPhone(String phone) throws Exception {
        User user = new User();
        user.setPhone(phone);
        User result = userMapper.selectOne(user);
        if(result != null){
            return "1";
        }
        return "0";
    }

    @Override
    public UserInfoTdo appAuthor(String openid) throws Exception {
        UserInfoTdo tdo = new UserInfoTdo();
        User user = new User();
        user.setWxOpenid(openid);
        User result = userMapper.selectOne(user);
        BeanUtils.copyProperties(result,tdo);
        tdo.setToken(aouthToken(result));
        return tdo;
    }

    @Override
    public User getUserInfoByToken(String token) {
        User user = new User();
        String ecode = redisClient.get(Constans.USER_TOKEN_KEY+token);
        String json = new String(Base64.getDecoder().decode(ecode));
        user = JSONObject.parseObject(json,User.class);
        user = userMapper.selectOne(user);
        return user;
    }

    /**
     * 同意平台授权
     * @param user
     * @return
     * @throws AppException
     */
    private String aouthToken(User user) throws AppException {
        String key = "";
        if(user == null){
            ExceptionUtil.throwException(ExceptionUtil.UN_REGIST,"用户未注册");
        } else {
            key = Base64.getEncoder().encodeToString(user.getPhone().getBytes());
            redisClient.setTime(Constans.USER_TOKEN_KEY+key,SysUtils.getToken(user),Constans.USER_TOKEN_TIME_OUT);
        }
        return key;
    }

//    public static void main(String[] args) {
//        User user = new User();
//        user.setWxOpenid("asdasd");
//        user.setPhone("121312321");
//        String ecode = "eyJjbG9zZVRpbWUiOiIyMDIwLTA0LTEzVDE1OjM4OjM2LjM0MSIsIm5pY2tOYW1lIjoibWkgbWFuY2hpIiwib3BwZW5JZCI6Im9WMkZ0NWJYNWZXaERJVW8xMG9pVmhHdVUyN2ciLCJwaG9uZSI6IjE4ODc0ODMwMzM2IiwidXNlcm5hbWUiOiJjaGVubWluIn0=";
//        System.out.println(ecode);
//        String json = new String(Base64.getDecoder().decode(ecode));
//        System.out.println(json);
//    }

//    public static void main(String[] args) {
//        String ecode = "Q2hlbm1pbjEyMzQ1Ng==";
//        System.out.println(ecode);
//        String json = new String(Base64.getDecoder().decode(ecode));
//        System.out.println(json);
//    }
}
