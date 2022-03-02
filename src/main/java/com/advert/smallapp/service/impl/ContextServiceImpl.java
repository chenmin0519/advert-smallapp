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
        String contextStr = "通知：\n发布人：" + context.getNickName() + "\n 班级："+context.getClassName()+" \n 发布内容："+context.getContext();
        contextStr += "\n 发布图片："+context.getImg() +"\n下线链接：http://112.124.34.251/yixiaoshui/context/disabled?id="+context.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        '{"msgtype": "text","text": {"content":"我就是我, 是不一样的烟火"}}'
        Map<String,Object> param = new HashMap<>();
        param.put("msgtype","text");
        Map<String,String> text = new HashMap<>();
        text.put("content",contextStr);
        param.put("text",text);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( robot_url, HttpMethod.GET, request , String.class );
        log.info("发消息结果：{}",JSONObject.toJSONString(response));
    }
}
