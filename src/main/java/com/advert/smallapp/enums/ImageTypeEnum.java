package com.advert.smallapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageTypeEnum {
    NAVIGATION("navigation_image","轮播图");
    private String key;
    private String value;
}
