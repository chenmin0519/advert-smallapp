package com.advert.smallapp.basic.controller;

import com.advert.smallapp.commons.ApiResult;
import com.advert.smallapp.enums.EnumItemVo;
import com.advert.smallapp.enums.ImageTypeEnum;
import com.advert.smallapp.exception.ExceptionUtil;
import com.advert.smallapp.tdo.EnumTdo;
import com.advert.smallapp.utils.ClassUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("/enums")
@Slf4j
public class EnumsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String enumPackageName = "com.advert.smallapp.enums";
    public static Map<String, Class<?>> enums = new TreeMap<>();
    static {
        Set<Class<?>> classSet = ClassUtil.getClassSet(enumPackageName);
        for (Class<?> class1 : classSet) {
            enums.put(class1.getSimpleName(), class1);
        }
    }

    @GetMapping(value = "/queryImageEnums")
    @ApiOperation("查询wheelplanting所有类型")
    public ApiResult<Map<String,String>> queryImageEnums() throws Exception{
        Map<String,String> f = new HashMap<>();
        for(ImageTypeEnum imageTypeEnum : ImageTypeEnum.values()){
            f.put(imageTypeEnum.getKey(),imageTypeEnum.getValue());
        }
        return ApiResult.success(f);
    }

    @ApiOperation("查询枚举列表")
    @GetMapping(value = "/getEnums")
    public ApiResult<List<EnumItemVo>> getEnums() {
        List<EnumItemVo> list = new ArrayList<>();
        Set<String> keys = enums.keySet();
        for (String key : keys) {
            Class class1 = enums.get(key);
            try {
                EnumItemVo vo = new EnumItemVo();
                //设置名称
                Field descField = class1.getField("desc");
                vo.setEnumName(descField.get(class1).toString());
                vo.setEnumCode(key);
                Object[] objects = class1.getEnumConstants();
                //获取指定方法
                Method getCode = class1.getMethod("getKey");
                Method getValue = class1.getMethod("getValue");
                Map<Object, Object> values = new TreeMap<>();
                for (Object object : objects) {
                    values.put(getCode.invoke(object), getValue.invoke(object));
                }
                vo.setEnumValues(values);
                list.add(vo);
            } catch (Exception e) {
                logger.warn("获取枚举失败；class:"+class1.getName(),e.getMessage());
            }
        }
        return ApiResult.success(list);
    }

    @ApiOperation("查询单个枚举")
    @GetMapping(value = "/getEnumsItem")
    public ApiResult<List<EnumTdo>> getEnumsItem(@ApiParam(value = "枚举编码（查询枚举列表中可以查看）",required=true)@RequestParam String enumCode ) {
        Class class1 = enums.get(enumCode);
        List<EnumTdo> values = new ArrayList<>();
        try {
            if(class1 == null) {
                ExceptionUtil.throwException(ExceptionUtil.ENUM_ERROR,"枚举不存在");
            }
            //设置名称
            Field descField = class1.getField("desc");
            Object[] objects = class1.getEnumConstants();
            //获取指定方法
            Method getCode = class1.getMethod("getKey");
            Method getValue = class1.getMethod("getValue");
            EnumTdo enumTdo = null;
            for (Object object : objects) {
                enumTdo = new EnumTdo();
                enumTdo.setEnumCode(getCode.invoke(object).toString());
                enumTdo.setEnumName(getValue.invoke(object).toString());
                values.add(enumTdo);
//                values.put(getCode.invoke(object), getValue.invoke(object));
            }
        } catch (Exception e) {
            logger.warn("获取枚举失败；class:"+class1.getName(),e.getMessage());
        }
        return ApiResult.success(values);
    }

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"1");
        map.put(4,"4");
        map.put(6,"6");
        map.put(5,"5");
        map.put(3,"3");
        map.put(2,"2");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(x->{
                    System.out.println(x.getValue());
                });
    }
}
