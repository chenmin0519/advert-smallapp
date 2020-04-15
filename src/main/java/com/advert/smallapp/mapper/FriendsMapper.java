package com.advert.smallapp.mapper;


import com.advert.smallapp.pojo.Friends;
import com.advert.smallapp.tdo.FriendsListTdo;
import com.advert.smallapp.tdo.FriendsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FriendsMapper extends CommonMapper<Friends>{
    /**
     * 取消点赞
     * @param id
     */
    @Update("update friends set likes = likes -1 where id = #{id}")
    void noLike(@Param("id") Long id);

    /**
     * 点赞
     * @param id
     */
    @Update("update friends set likes = likes +1 where id = #{id}")
    void like(@Param("id")Long id);
    /**
     * 评论
     * @param id
     */
    @Update("update friends set comment = comment -1 where id = #{id}")
    void noComment(@Param("id") Long id);

    /**
     * 删除评论
     * @param id
     */
    @Update("update friends set comment = comment +1 where id = #{id}")
    void comment(@Param("id")Long id);


    @Select("<script>select a.id,a.name,a.icon,a.content,a.images,count(b.id) as likeNum,group_concat(b.`name`) as zanSourceStr from friends a left join friends_detail b " +
            " on a.id = b.friends_id and b.type=1 where 1=1 " +
            "<if test = 'userId != null'> and user_id = #{userId}</if>" +
            "<if test = 'orderBy != null'> order by #{orderBy} </if>" +
            "<if test = 'orderBy == null'> order by id desc</if></script>")
    List<FriendsListTdo> loadPage(FriendsQuery queryPo);
}
