package com.advert.smallapp.service.impl;

import com.advert.smallapp.config.Constans;
import com.advert.smallapp.mapper.AreaMapper;
import com.advert.smallapp.pojo.Area;
import com.advert.smallapp.service.AreaService;
import com.advert.smallapp.tdo.AreaAll;
import com.advert.smallapp.utils.RedisClient;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public AreaAll getDefault() {
        AreaAll areaAll = new AreaAll();
        String json = null;
        try{
            json = redisClient.get(Constans.AREA_INFO_KEY);
        }catch (Exception e){
            logger.info("redis挂了连不上了");
        }
        if(json != null){
            try{
                areaAll = JSONObject.parseObject(json,AreaAll.class);
                return areaAll;
            }catch (Exception e){
                logger.info("解析redis有问题");
            }
        }
        Example example = new Example(Area.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("level","1");
        example.setOrderByClause("id asc ");
        List<Area> provinces = areaMapper.selectByExample(example);
        areaAll.setProvinces(provinces);
        Area param = new Area();
        param.setLevel(2);
        List<Area> citys = areaMapper.select(param);
        Map<Long,List<Area>> cityMap = new HashMap<>();
        //格式化成需要的格式 城市
        provinces.forEach(province->{
            List<Area> provinceCitys = new ArrayList<>();
            citys.forEach(city->{
                if(city.getParentId().equals(province.getId())) {
                    provinceCitys.add(city);
                }
            });
            cityMap.put(province.getId(),provinceCitys);
        });
        areaAll.setCitys(cityMap);
        //区县
        param.setLevel(3);
        List<Area> areas = areaMapper.select(param);
        Map<Long,List<Area>> areaMap = new HashMap<>();
        citys.forEach(city->{
            List<Area> citysAreas = new ArrayList<>();
            areas.forEach(area->{
                if(area.getParentId().equals(city.getId())) {
                    citysAreas.add(area);
                }
            });
            areaMap.put(city.getId(),citysAreas);
        });
        areaAll.setAreas(areaMap);
        redisClient.set(Constans.AREA_INFO_KEY,JSONObject.toJSONString(areaAll));
        return areaAll;
    }

    @Override
    public List<Area> getByParent(Long parentId) {
        Area recode = new Area();
        recode.setParentId(parentId);
        return areaMapper.select(recode);
    }
}
