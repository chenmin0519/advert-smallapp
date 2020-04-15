package com.advert.smallapp.basic.controller;


import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.config.TokenCheck;
import com.advert.smallapp.enums.StatusEnum;
import com.advert.smallapp.pojo.Friends;
import com.advert.smallapp.pojo.FriendsDetail;
import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.service.FriendsService;
import com.advert.smallapp.tdo.FriendsListTdo;
import com.advert.smallapp.tdo.FriendsQuery;
import com.advert.smallapp.tdo.GoodsQuery;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/friends")
@Slf4j
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @TokenCheck
    @GetMapping("/saveFriends")
    @ApiOperation("发布朋友圈")
    public ApiResult<Boolean> saveFriends(
            @RequestParam(name = "images",required = false) String images,
            @RequestParam(name = "comment",required = true) String comment,
            @RequestParam(name = "title",required = true) String title,
            @RequestParam(name = "userId",required = true) Long userId,
            @RequestParam(name = "icon",required = true) String icon,
            @RequestParam(name = "name",required = true) String name){
        Friends friends = new Friends();
        friends.setAddDate(new Date());
        friends.setStatus(StatusEnum.YES.getKey());
        friends.setComment(0l);
        friends.setContent(comment);
        friends.setIcon(icon);
        friends.setImages(images);
        friends.setLikes(0l);
        friends.setName(name);
        friends.setUserId(userId);
        friends.setTitle(title);
        friendsService.saveFriends(friends);
        return ApiResult.success(true);
    }

    @PostMapping(value = "/loadPage")
    @ApiOperation("分页查询")
    public PageInfo<FriendsListTdo> loadPage(@RequestBody PageQuery<FriendsQuery> query){
        return friendsService.loadPage(query);
    }

    @TokenCheck
    @GetMapping(value = "/like")
    @ApiOperation("点赞")
    public ApiResult<Boolean> like(@RequestParam(name = "status")@ApiParam("1取消0点赞") Integer status,
                                   @RequestParam(name = "id") Long id,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "name") String name){
        friendsService.like(status,id,userId,name);
        return ApiResult.success(true);
    }

    @TokenCheck
    @GetMapping(value = "/saveComment")
    @ApiOperation("评论")
    public ApiResult<Boolean> saveComment(@RequestParam(name = "id") Long id,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "name") String name,
                                   @RequestParam(name = "content") String content){
        FriendsDetail friendsDetail = new FriendsDetail();
        friendsDetail.setFriendsId(id);
        friendsDetail.setName(name);
        friendsDetail.setUserId(userId);
        friendsDetail.setContent(content);
        friendsService.saveComment(friendsDetail);
        return ApiResult.success(true);
    }
}
