package com.advert.smallapp.service;

import com.advert.smallapp.pojo.User;
import com.advert.smallapp.tdo.UserInfoTdo;
import com.advert.smallapp.tdo.UserTdo;
import com.advert.smallapp.tdo.WechatOpenidDTO;

public interface UserService {

    String login(String phone, String password) throws Exception;

    UserInfoTdo regist(User user) throws Exception;

    UserInfoTdo appAuthor(String openid) throws Exception;

    String checkPhone(String phone) throws Exception;

    User getUserInfoByToken(String token);
}
