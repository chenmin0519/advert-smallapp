package com.advert.smallapp.tdo;

import lombok.Data;

@Data
public class UserTdo {
    public Long id;
    private String username;
    private String password;
    private String phone;
}
