package com.advert.smallapp.service;

import com.advert.smallapp.pojo.Context;

public interface ContextService {

    Boolean save(Context context);

    void disabled(Long id);
}
