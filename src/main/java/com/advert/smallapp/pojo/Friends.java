package com.advert.smallapp.pojo;

import com.advert.smallapp.commons.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@ApiModel("朋友圈")
@Table(name = "friends")
@Data
public class Friends extends MainPO {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ApiModelProperty("标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty("信息")
    @Column(name = "content")
    private String content;

    @ApiModelProperty("点赞数")
    @Column(name = "likes")
    private Long likes;
    @ApiModelProperty("评论数")
    @Column(name = "comment")
    private Long comment;

    @ApiModelProperty("1正常")
    @Column(name = "status")
    private Integer status;

    @Column(name = "images")
    private String images;

    @Column(name = "icon")
    private String icon;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "add_date")
    private Date addDate;
}
