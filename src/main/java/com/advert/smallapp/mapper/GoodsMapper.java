package com.advert.smallapp.mapper;

import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.tdo.GoodsQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsMapper  extends CommonMapper<Goods>{

    @Select("<script>select * from goods where 1=1 " +
            "<if test = 'isRecommend != null'> and is_recommend = #{isRecommend}</if>" +
            "<if test = 'category != null'> and category = #{category}</if>" +
            "<if test = 'isHot != null'> and is_hot = #{isHot}</if>" +
            "<if test = 'areaCode != null'> and area_code like concat('#{areaCode}','%')</if>" +
            "<if test = 'orderBy != null'> order by #{orderBy},id desc</if>" +
            "<if test = 'orderBy == null'> order by id desc</if></script>")
    List<Goods> loadPage(GoodsQuery queryPo);

    @Update("update goods set read_num = read_num+1 where id = #{id}")
    void addReadNum(@Param("id") Long id);
}
