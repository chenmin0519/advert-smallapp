package com.advert.smallapp.exception;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.ConstructorArgs;

/**
 *  自定义异常类
 * */
@Data
@AllArgsConstructor
public class AppException extends Exception{
    //异常码
    private Long code;
    //异常信息
    private String message;

}
