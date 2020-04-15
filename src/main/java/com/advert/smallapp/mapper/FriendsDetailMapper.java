package com.advert.smallapp.mapper;

import com.advert.smallapp.pojo.Friends;
import com.advert.smallapp.pojo.FriendsDetail;
import com.advert.smallapp.tdo.FriendsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendsDetailMapper extends CommonMapper<FriendsDetail>{

}
