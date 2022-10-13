package com.advert.smallapp.service.impl;

import com.advert.smallapp.commons.PageQuery;
import com.advert.smallapp.mapper.ContextMapper;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.service.ContextService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@Service
@Slf4j
public class ContextServiceImpl implements ContextService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ContextMapper contextMapper;


    @Override
    public PageInfo<Context> page(PageQuery<Context> query) {
        PageInfo<Context> friends = PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPageInfo(
                () -> contextMapper.loadPage(query.getQueryPo()));
        return friends;
    }

    @Override
    public void disabled(Long id) {
        Context context = new Context();
        context.setId(id);
        context.setStatus(2);
        contextMapper.updateByPrimaryKeySelective(context);
    }

    @Override
    public void up(Long id) {
        Context context = new Context();
        context.setId(id);
        context.setStatus(1);
        contextMapper.updateByPrimaryKeySelective(context);
    }

    @Override
    public Boolean save(Context context) {
        context.setStatus(1);
        int count = contextMapper.insertSelective(context);
        if(count > 0) {
            sendMessage(context);
            return true;
        }
        return false;
    }


    String robot_url = "https://oapi.dingtalk.com/robot/send?access_token=cc1a022546457d393aa3d5af9bc1bd1998a454978b2e8ebf86bf688b08dc587c";
    private void sendMessage(Context context){
        String contextStr = "通知：\n发布人：" + context.getNickName() + "\n班级："+context.getClassName()+" \n发布内容："+context.getContext();
        contextStr += "\n发布的图片链接："+context.getImg() + "\n禁用链接：http://112.124.34.251/yixiaoshui/context/disabled?id="+context.getId();
        contextStr += "\n启用链接：http://112.124.34.251/yixiaoshui/context/up?id="+context.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        '{"msgtype": "text","text": {"content":"我就是我, 是不一样的烟火"}}'
        Map<String,Object> param = new HashMap<>();
        param.put("msgtype","text");
        Map<String,String> text = new HashMap<>();
        text.put("content",contextStr);
        param.put("text",text);
        log.info("参数：{}",JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( robot_url, HttpMethod.POST, request , String.class );
        log.info("发消息结果：{}",JSONObject.toJSONString(response));
    }

    public static void main(String[] args) {
//        String str = "http://smsapi.5taogame.com/sms/httpSmsInterface2";
//        Map<String,Object> param = new HashMap<>();
//        param.put("userId","csjixing");
//        param.put("account","csjixing");
//        param.put("password","csjixing");
//        param.put("mobile","18874830336");
//        param.put("content","【趣提资讯】您的验证为1234， 5分钟内有效。");
//        param.put("action","sendhy");
////        param.put("checkcontent","1");
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        System.out.println("参数："+JSONObject.toJSONString(param));
//        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
//        ResponseEntity<String> response = restTemplate.exchange( str, HttpMethod.POST, request , String.class );
//        System.out.println(JSONObject.toJSONString(response.getBody()));
//        https://rxlwpheczl961184.su.bcebos.com/qxyfrrwakmtemp.html?s=MVvls
        String str = "https://blswgahupe357841-1313036676.cos.ap-nanjing.myqcloud.com/xrbjlmfqlo95570d30-0c8a-469a-8a05-55dc511f540e.html";
//        Map<String,Object> param = new HashMap<>();
//        param.put("userId","csjixing");
//        param.put("account","csjixing");
//        param.put("password","csjixing");
//        param.put("mobile","18874830336");
//        param.put("content","【趣提资讯】您的验证为1234， 5分钟内有效。");
//        param.put("action","sendhy");
//        param.put("checkcontent","1");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        System.out.println("参数："+JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange( str, HttpMethod.GET, request , String.class );
        if(response.getBody().contains("https://o.jixinwangluo.cn/static/js/core.min.js")){
            System.out.println(true);
        }
        System.out.println(JSONObject.toJSONString(response.getBody()));
    }
}
