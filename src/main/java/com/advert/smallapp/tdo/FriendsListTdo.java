package com.advert.smallapp.tdo;

import lombok.Data;

import java.util.List;

@Data
public class FriendsListTdo {
    private Long id;
    private String icon;
    private String content;
    private String name;
    private Boolean isLike = false;
    private String images;
    private List<String> zanSource;
    private String zanSourceStr;
    private Integer likeNum = 0;
    private List<FriendsCommentTdo> contnets;
}
