package com.advert.smallapp.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeEnum {
    INTERNET_BAR(1,"网吧桌面广告"),
    OUTDOORS(2,"户外"),
    BANNER(3,"横幅"),
    SHOP(4,"商店"),
    CAMPUS(5,"校圆"),
    APP(6,"app线上"),
    OTHER(7,"其他");
    public static String desc = "广告类型枚举";
    private Integer key;
    private String value;
}
