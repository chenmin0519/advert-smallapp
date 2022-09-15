package com.advert.smallapp;

import com.advert.smallapp.interceptor.CrossDomainInterceptor;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.TimeZone;


@Configuration
public class MvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	@Autowired
	CrossDomainInterceptor crossDomainInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(crossDomainInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}


}
