package com.advert.smallapp.service;

import com.advert.smallapp.tdo.GoodsSaveDto;

public interface GoodsService {
    /**
     * 保存商品
     * @param saveDto
     */
    void save(GoodsSaveDto saveDto);
}
