package com.advert.smallapp.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

public class ComsumeHistoryController {
    //日志里面补数据
    public static void main(String[] args) throws IOException {
        FileReader fileReader1 = new FileReader("C:/Users/18874/Workspaces/small_1.log");
        BufferedReader br1 = null;
        System.out.println("开始");
        try {
            br1 = new BufferedReader(fileReader1);
            boolean flag = false;
            while (true) {
                String url = "";
                String rederStr = br1.readLine();
                if (rederStr.contains("reportData,参数:")) {
                    url = "curl \"http://127.0.0.1:9002/mobile/exportUserDayReport/reportData?";
                    String str[] = rederStr.split("reportData,参数:");
                    String paramsStr[] = str[1].split(",返回结果=");
                    ComsumeHistoryController u = new ComsumeHistoryController();
                    DataModel param = u.formatParam(paramsStr[0]);
                    if(param.getGameId() != null) {
                        url += "importGameId=" + param.getGameId();
                    }
                    if(param.getSceneName() != null) {
                        url += "&sceneName="+param.getSceneName();
                    }
                    if(param.getType() != null) {
                        url += "&type="+param.getType();
                    }
                    if(param.getExportGameId() != null) {
                        url += "&exportGameId="+param.getExportGameId();
                    }
                    if(param.getAppversion() != null) {
                        url += "&appversion="+param.getAppversion();
                    }
                    if(param.getPosition() != null) {
                        url += "&position="+param.getPosition();
                    }
                    if(param.getGameUserId() != null) {
                        url += "&gameUserId="+param.getGameUserId();
                    }
                    url += "&token=888&oauthId=888&userToken=888\"";
                    System.out.println(url);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("读完第一个");
            try {
                br1.close();
                fileReader1.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    public DataModel formatParam( String sss) {
        DataModel map = new DataModel();
        String paramStr[] = sss.split(",");
        for(String param : paramStr){
            String params[] = param.split("=");
            if("gameId".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setGameId(Long.valueOf(params[1]));
                }
            }
            if("exportGameId".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setExportGameId(Long.valueOf(params[1]));
                }
            }
            if("sceneName".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setSceneName(params[1].substring(1,params[1].length()-1));
                }
            }
            if("gameUserId".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setGameUserId(Long.valueOf(params[1]));
                }
            }
            if("appversion".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setAppversion(params[1].substring(1,params[1].length()-1));
                }
            }
            if("position".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setPosition(params[1].substring(1,params[1].length()-1));
                }
            }
            if("type".equals(params[0])){
                if(!"null".equals(params[1])){
                    map.setType(Integer.parseInt(params[1]));
                }
            }
        }
       return map;
    }
}
