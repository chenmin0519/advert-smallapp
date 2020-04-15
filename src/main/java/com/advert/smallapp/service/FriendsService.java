package com.advert.smallapp.service;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.pojo.Friends;
import com.advert.smallapp.pojo.FriendsDetail;
import com.advert.smallapp.tdo.FriendsListTdo;
import com.advert.smallapp.tdo.FriendsQuery;
import com.github.pagehelper.PageInfo;

public interface FriendsService {
    /**
     * 发信息
     * @param friends
     */
    void saveFriends(Friends friends);

    /**
     * 点赞或取消 1点赞
     * @param status
     * @param id
     * @param userId
     */
    void like(Integer status,Long id,Long userId,String name);

    /**
     *
     * @param friendsDetail
     */
    void saveComment(FriendsDetail friendsDetail);

    /**
     * 分页
     * @param query
     * @return
     */
    PageInfo<FriendsListTdo> loadPage(PageQuery<FriendsQuery> query);
}
