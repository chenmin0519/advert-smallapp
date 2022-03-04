package com.advert.smallapp.service;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.pojo.Context;
import com.github.pagehelper.PageInfo;

public interface ContextService {

    Boolean save(Context context);

    void disabled(Long id);

    PageInfo<Context> page(PageQuery<Context> query);

    void up(Long id);
}
