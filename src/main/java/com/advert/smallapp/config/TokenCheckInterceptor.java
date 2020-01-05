package com.advert.smallapp.config;

import com.advert.smallapp.commons.AdvertExceptionHandler;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.tdo.UserTdo;
import com.advert.smallapp.utils.RedisClient;
import com.advert.smallapp.utils.SysUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component
public class TokenCheckInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handler2 = (HandlerMethod) handler;
        TokenCheck check = handler2.getMethodAnnotation(TokenCheck.class);
        if(check != null) {
            //需要授权接口校验
            String token = request.getParameter("token");
            String json = redisClient.get(Constans.USER_TOKEN_KEY + token);
            if (json != null) {
                String userJson = SysUtils.decodeToken(json);
                try {
                    UserTdo userTdo = JSONObject.parseObject(userJson,UserTdo.class);
                }catch (Exception e){
                    ExceptionUtil.throwException(ExceptionUtil.DECODE_USER_ERROR,"token无效请重新登录");
                }
            }else {
                ExceptionUtil.throwException(ExceptionUtil.DECODE_USER_ERROR,"token无效请重新登录");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
