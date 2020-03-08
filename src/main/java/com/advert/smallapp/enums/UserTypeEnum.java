package com.advert.smallapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserTypeEnum {
    MOBILE(1,"手机"),
    PC(2,"电脑");
    private Integer key;
    private String value;
}
