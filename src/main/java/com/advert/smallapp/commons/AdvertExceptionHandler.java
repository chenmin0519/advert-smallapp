package com.advert.smallapp.commons;

import com.advert.smallapp.exception.AppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ControllerAdvice + @ExceptionHandler 实现全局的 Controller 层的异常处理
 */
@ControllerAdvice
public class AdvertExceptionHandler {
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ApiResult handlerException(Exception e){
        if(e instanceof AppException){
            return ApiResult.error(((AppException) e).getCode(),e.getMessage());
        }
        e.printStackTrace();
        //未知异常
        return ApiResult.error(600L,"系统异常");
    }
}
