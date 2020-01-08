package com.advert.smallapp.service;

import com.advert.smallapp.pojo.WheelPlanting;

import java.util.List;

public interface WheelPlantingService {
    /**
     * 查询轮播图
     * @return
     */
    List<WheelPlanting> queryNavigation();

    /**
     * 保存
     * @param param
     */
    void saveImages(WheelPlanting param);
}
