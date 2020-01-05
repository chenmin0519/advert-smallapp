package com.advert.smallapp.service;

import com.advert.smallapp.pojo.User;
import com.advert.smallapp.tdo.UserTdo;
import com.advert.smallapp.tdo.WechatOpenidDTO;

public interface UserService {

    String login(String phone, String password) throws Exception;

    String regist(User user) throws Exception;

    String appAuthor(String openid) throws Exception;

}
