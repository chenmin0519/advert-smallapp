package com.advert.smallapp.service.impl;

import com.advert.smallapp.config.Constans;
import com.advert.smallapp.exception.AppException;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.mapper.UserMapper;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.advert.smallapp.utils.RedisClient;
import com.advert.smallapp.utils.SysUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
    public String regist(User user) throws Exception {
        validataUserInfo(user);
        if(user.getPassword() != null){
            user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        }
        userMapper.insertSelective(user);
        return aouthToken(user);
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
    public String appAuthor(String openid) throws Exception {
        User user = new User();
        user.setWxOpenid(openid);
        User result = userMapper.selectOne(user);
        return aouthToken(result);
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
            redisClient.set(Constans.USER_TOKEN_KEY+key,SysUtils.getToken(user));
        }
        return key;
    }
}
