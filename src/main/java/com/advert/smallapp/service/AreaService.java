package com.advert.smallapp.service;

import com.advert.smallapp.pojo.Area;
import com.advert.smallapp.tdo.AreaAll;

import java.util.List;

public interface AreaService {
    AreaAll getDefault();

    List<Area> getByParent(Long parentId);
}
