package com.advert.smallapp.pojo;

import com.advert.smallapp.commons.MainPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel("朋友圈点赞评论详情")
@Table(name = "friends_detail")
@Data
public class FriendsDetail extends MainPO {
    @Id
    @OrderBy("desc")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ApiModelProperty("")
    @Column(name = "friends_id")
    public Long friendsId;
    @ApiModelProperty("//类型1点赞2评论")
    @Column(name = "type")
    public Integer type;
    @Column(name = "images")
    public String images;
    @Column(name = "user_id")
    public Long userId;
    @Column(name = "content")
    public String content;
    @Column(name = "name")
    public String name;

}
