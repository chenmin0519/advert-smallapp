package com.advert.smallapp.service.impl;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.enums.FriendsDetailTypeEnum;
import com.advert.smallapp.mapper.FriendsDetailMapper;
import com.advert.smallapp.mapper.FriendsMapper;
import com.advert.smallapp.mapper.UserMapper;
import com.advert.smallapp.pojo.Friends;
import com.advert.smallapp.pojo.FriendsDetail;
import com.advert.smallapp.pojo.User;
import com.advert.smallapp.service.FriendsService;
import com.advert.smallapp.tdo.FriendsCommentTdo;
import com.advert.smallapp.tdo.FriendsListTdo;
import com.advert.smallapp.tdo.FriendsQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zaxxer.hikari.util.FastList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsDetailMapper  friendsDetailMapper;

    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveFriends(Friends friends) {
        friends.setStatus(1);
        friendsMapper.insertSelective(friends);
    }

    @Override
    public void like(Integer status,Long id,Long userId,String name) {
        FriendsDetail friendsDetail = new FriendsDetail();
        friendsDetail.setType(FriendsDetailTypeEnum.LIKE.getKey());
        friendsDetail.setFriendsId(id);
        friendsDetail.setUserId(userId);
        friendsDetail.setName(name);
        if(status == 1){
            friendsMapper.noLike(id);
            friendsDetailMapper.delete(friendsDetail);
        }else{
            friendsMapper.like(id);
            friendsDetailMapper.insertSelective(friendsDetail);
        }
    }

    @Override
    public void saveComment(FriendsDetail friendsDetail) {
        friendsDetail.setType(FriendsDetailTypeEnum.COMMENT.getKey());
        friendsDetailMapper.insertSelective(friendsDetail);
        friendsMapper.comment(friendsDetail.getFriendsId());
    }

    @Override
    public PageInfo<FriendsListTdo> loadPage(PageQuery<FriendsQuery> query) {
        PageInfo<FriendsListTdo> friends = PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPageInfo(
                () -> friendsMapper.loadPage(query.getQueryPo()));
        for(FriendsListTdo item : friends.getList()){
            item.setZanSource(Arrays.asList(item.getZanSourceStr().split(",")));
            FriendsDetail friendsDetail = new FriendsDetail();
            if(query.getQueryPo() !=null && query.getQueryPo().getLikeUserId() != null){
                friendsDetail.setFriendsId(item.getId());
                friendsDetail.setUserId(query.getQueryPo().getLikeUserId());
                int count = friendsDetailMapper.selectCount(friendsDetail);
                item.setIsLike(count>0);
            }
            friendsDetail = new FriendsDetail();
            friendsDetail.setFriendsId(item.getId());
            friendsDetail.setType(FriendsDetailTypeEnum.COMMENT.getKey());
            List<FriendsDetail> friendsDetails = friendsDetailMapper.select(friendsDetail);
            List<FriendsCommentTdo> commentTdos = new ArrayList<>();
            for(FriendsDetail detail : friendsDetails){
                FriendsCommentTdo tdo = new FriendsCommentTdo();
                tdo.setContent(detail.getContent());
                tdo.setFirstname(detail.getName());
                commentTdos.add(tdo);
            }
            item.setContnets(commentTdos);
        }
        return friends;
    }
}
