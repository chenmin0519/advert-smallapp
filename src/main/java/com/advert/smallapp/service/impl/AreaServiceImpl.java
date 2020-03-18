package com.advert.smallapp.service.impl;

import com.advert.smallapp.mapper.AreaMapper;
import com.advert.smallapp.pojo.Area;
import com.advert.smallapp.service.AreaService;
import com.advert.smallapp.tdo.AreaAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public AreaAll getDefault() {
        AreaAll areaAll = new AreaAll();
        Example example = new Example(Area.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("level","1");
        example.setOrderByClause("id asc ");
        List<Area> provinces = areaMapper.selectByExample(example);
        areaAll.setProvinces(provinces);
        Area param = new Area();
        param.setParentId(provinces.get(0).getId());
        List<Area> citys = areaMapper.select(param);
        areaAll.setCitys(citys);
        param = new Area();
        param.setParentId(citys.get(0).getId());
        List<Area> areas = areaMapper.select(param);
        areaAll.setAreas(areas);
        return areaAll;
    }

    @Override
    public List<Area> getByParent(Long parentId) {
        Area recode = new Area();
        recode.setParentId(parentId);
        return areaMapper.select(recode);
    }
}
