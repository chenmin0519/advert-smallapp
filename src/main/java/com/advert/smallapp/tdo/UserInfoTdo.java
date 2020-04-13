package com.advert.smallapp.tdo;

import com.advert.smallapp.pojo.User;
import lombok.Data;

@Data
public class UserInfoTdo extends User {

    private String token;
}
