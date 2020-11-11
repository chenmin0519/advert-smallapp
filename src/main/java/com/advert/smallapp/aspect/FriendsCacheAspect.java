package com.advert.smallapp.aspect;

import com.advert.smallapp.config.Constans;
import com.advert.smallapp.tdo.FriendsListTdo;
import com.advert.smallapp.utils.RedisClient;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FriendsCacheAspect {

    private static final String FRIENDS_PAGE_KEY = "FRIENDS_PAGE_KEY_";

    @Autowired
    RedisClient redisClient;

    /**
     * 缓存朋友圈列表信息
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.advert.smallapp.service.FriendsService.loadPage(..))")
    public Object cacheLoadPage(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String json = null;
        try {
            json = redisClient.get(FRIENDS_PAGE_KEY + JSONObject.toJSONString(args[0]));
        }catch (Exception e){}
        if (json != null) {
            return JSONObject.parseObject(json, PageInfo.class);
        }
        PageInfo result = (PageInfo) joinPoint.proceed(args);
        try {
            redisClient.setTime(FRIENDS_PAGE_KEY+JSONObject.toJSONString(args[0]),
                    JSONObject.toJSONString(result),Constans.FRIENDS_PAGE_CACHE_TIME_OUT);
        }catch (Exception e){}
        return result;
    }

    /**
     * 清空朋友圈列表缓存
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.advert.smallapp.service.FriendsService.saveFriends(..))")
    public void saveFriends(JoinPoint joinPoint) throws Throwable {
        try {
            delFriendsPageCache(FRIENDS_PAGE_KEY);
        }catch (Exception e){}
    }

    /**
     * 清空朋友圈列表缓存
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.advert.smallapp.service.FriendsService.saveComment(..))")
    public void saveComment(JoinPoint joinPoint) throws Throwable {
        try {
            delFriendsPageCache(FRIENDS_PAGE_KEY);
        }catch (Exception e){}
    }
    /**
     * 清空朋友圈列表缓存
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.advert.smallapp.service.FriendsService.like(..))")
    public void like(JoinPoint joinPoint) throws Throwable {
        try {
            delFriendsPageCache(FRIENDS_PAGE_KEY);
        }catch (Exception e){}
    }
    /**
     * 缓存朋友圈
     * @param key
     * @param json
     */
    private void setFriendsPageCache(String key,String json){
        redisClient.setTime(key,json,Constans.FRIENDS_PAGE_CACHE_TIME_OUT);
    }

    /**
     * 模糊清空缓存
     * @param key
     */
    private void delFriendsPageCache(String key){
        redisClient.delAll(key);
    }
}
