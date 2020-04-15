package com.advert.smallapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FriendsDetailTypeEnum {
    LIKE(1,"点赞"),
    COMMENT(2,"评论");
    public static String desc = "朋友圈点赞评论类型";
    private Integer key;
    private String value;
}
