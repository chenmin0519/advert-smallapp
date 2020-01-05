package com.advert.smallapp.tdo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTdo {
    private String nickName;
    private String username;
    private String oppenId;
    private String phone;
    private LocalDateTime closeTime;
} 
