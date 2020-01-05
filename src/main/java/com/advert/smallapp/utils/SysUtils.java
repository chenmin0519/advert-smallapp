package com.advert.smallapp.utils;

import com.advert.smallapp.pojo.User;
import com.advert.smallapp.tdo.UserTdo;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.util.Base64;

public class SysUtils {

    /**
     * 根据用户信息生成token
     * @param user
     * @return
     */
    public static String getToken(User user){
        UserTdo userTdo = new UserTdo();
        userTdo.setPhone(user.getPhone());
        userTdo.setUsername(user.getUsername());
        userTdo.setOppenId(user.getWxOpenid());
        userTdo.setNickName(user.getNickname());
        userTdo.setCloseTime(LocalDateTime.now().plusMinutes(30L));
        String str = JSONObject.toJSONString(userTdo);
        return  Base64.getEncoder().encodeToString(str.getBytes());
    }
    public static String decodeToken(String token){
        return new String(Base64.getDecoder().decode(token));
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("chenmin");
        user.setWxOpenid("612893612984");
        user.setPhone("18874830336");
        System.out.println(getToken(user));
        System.out.println(getToken(user).length());
        System.out.println(new String(Base64.getDecoder().decode(getToken(user))));
    }
}
