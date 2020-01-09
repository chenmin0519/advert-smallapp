package com.advert.smallapp.service.impl;

import com.advert.smallapp.enums.ImageTypeEnum;
import com.advert.smallapp.mapper.WheelPlantingMapper;
import com.advert.smallapp.pojo.WheelPlanting;
import com.advert.smallapp.service.WheelPlantingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WheelPlantingServiceImpl implements WheelPlantingService {

    @Autowired
    private WheelPlantingMapper wheelPlantingMapper;


    @Override
    public List<WheelPlanting> queryNavigation() {
        WheelPlanting query = new WheelPlanting();
        query.setType(ImageTypeEnum.NAVIGATION.getKey());
        List<WheelPlanting> wheelPlantings = wheelPlantingMapper.select(query);
        return wheelPlantings;
    }

    @Override
    public void saveImages(WheelPlanting param) {
        if(param.getId() != null) {
            wheelPlantingMapper.updateByPrimaryKeySelective(param);
        }else {
            wheelPlantingMapper.insertSelective(param);
        }
    }
}
