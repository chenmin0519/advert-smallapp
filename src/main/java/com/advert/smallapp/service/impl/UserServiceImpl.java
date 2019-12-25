package com.advert.smallapp.service.impl;

import com.advert.smallapp.mapper.UserMapper;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.UserService;
import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(String phone, String password){
        User query = new User();
        query.setPhone(phone);
        query.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
        User result = userMapper.selectOne(query);
        return result;
    }

    @Override
    public User regist(User user) throws Exception {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public String appAuthor(String openid) throws Exception {
        User user = new User();
        user.setWxOpenid(openid);
        User result = userMapper.selectOne(user);
        if(result == null){
           throw new Exception("未注册");
        } else {
            user.setUsername(result.getUsername());
            user.setPhone(result.getPhone());
            String str = JSONObject.toJSONString(user);
            str = Base64.getEncoder().encodeToString(str.getBytes());
            return str;
        }
    }

    @Override
    public User smallAppOauth(WechatOpenidDTO wod) throws Exception {
        if(wod.getOpenid() != null){
            User user = new User();
            user.setWxOpenid(wod.getOpenid());
            User result = userMapper.selectOne(user);
            if(result != null){
                return result;
            }
            result.setWxOpenid(wod.getOpenid());
//            result.set
        }
        return null;
    }
}
