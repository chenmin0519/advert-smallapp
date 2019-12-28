package com.advert.smallapp.exception;

/**
 * 异常code和信息处理
 * */
public class ExceptionUtil {
    public static final Long NOT_FOUND = 404L; //未找到地址
    public static final Long UN_REGIST = 600L; //未注册

    public static AppException throwException(Long code, String message){
        return new AppException(code,message);
    }
}
