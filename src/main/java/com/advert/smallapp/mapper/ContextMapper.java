package com.advert.smallapp.mapper;

import com.advert.smallapp.pojo.Context;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ContextMapper  extends CommonMapper<Context>{

    @Select("<script> select id,img,nick_name as nickName,class_name as className,create_time as createTime,modify_time as modifyTime,context from context where `status` = 1 " +
            "order by id desc </script>")
    List<Context> loadPage(Context queryPo);
}
