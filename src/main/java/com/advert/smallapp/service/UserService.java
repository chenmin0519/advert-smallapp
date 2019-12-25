package com.advert.smallapp.service;

import com.advert.smallapp.pojo.User;
import com.advert.smallapp.tdo.UserTdo;
import com.advert.smallapp.tdo.WechatOpenidDTO;

public interface UserService {

    User login(String phone, String password) throws Exception;

    User regist(User user) throws Exception;

    String appAuthor(String openid) throws Exception;

    /**
     * 小程序授权接口
     * @return
     */
    User smallAppOauth(WechatOpenidDTO wod) throws Exception;
}
