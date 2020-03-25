package com.advert.smallapp.utils;

import com.advert.smallapp.pojo.User;
import com.advert.smallapp.tdo.UserTdo;
import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 准备要加密的数据
            byte[] b = str.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'a', 'b', 'c', 'd', 'e','f' };
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
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
