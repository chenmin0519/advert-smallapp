package com.advert.smallapp.service;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.pojo.Goods;
import com.advert.smallapp.tdo.GoodsSaveDto;
import com.github.pagehelper.PageInfo;

public interface GoodsService {
    /**
     * 保存商品
     * @param saveDto
     */
    void save(GoodsSaveDto saveDto);

    /**
     * 分页
     * @param query
     * @return
     */
    PageInfo<Goods> loadPage(PageQuery<Goods> query);
}
