package com.advert.smallapp.service.impl;

import com.advert.smallapp.mapper.ContextMapper;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.service.ContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContextServiceImpl implements ContextService {

    @Autowired
    private ContextMapper contextMapper;


    @Override
    public Boolean save(Context context) {
        int count = contextMapper.insertSelective(context);
        if(count > 0)
            return true;
        return false;
    }
}
