package com.advert.smallapp.tdo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private List<String> resource;
    private String zanSourceStr;
    private Integer likeNum = 0;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addDate;
    private List<FriendsCommentTdo> contnets;
}
