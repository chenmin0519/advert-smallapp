package com.advert.smallapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageTypeEnum {
    NAVIGATION("navigation_image","导航图"),
    MAIN_ICON("main_icon","主图标"),
    WHEEL_PLANTING("wheel_planting","轮播图");
    public static String desc = "图片类型";
    private String key;
    private String value;
}
