package com.advert.smallapp.service.impl;

import com.advert.smallapp.basic.controller.PayReturn;
import com.advert.smallapp.pojo.AccessTokenVo;
import com.advert.smallapp.pojo.SendMessageParamVo;
import com.advert.smallapp.pojo.SendMessageResultVo;
import com.advert.smallapp.service.Test;
import com.advert.smallapp.utils.SysUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.DelayQueue;

@Service
public class TestImpl implements Test {

//    @Autowired
//    private RestTemplate restTemplate;


    @Override
    public String newPay() {
        String url = "http://47.74.180.115:8001/api/order/";
        return null;
    }

    @Override
    public String sms(String mobile) {
        String url = "https://pay-test.upayout.com/sms/send";
//        phone    // string  用户的电话号码
//                deviceId // string  必填，用户的设备id，用于限制接口滥用
//        userIp   // string  必填，用户的IP，用于限制接口滥用
//                captcha  // string  必填，验证码，一般为4 -6位数字
//        content  // string  可选，完整的消息内容（通道不限制发送模板情况下直接使用此内容）；
        TreeMap<String,String> ss = new TreeMap<>();
        ss.put("appId","devTestAppId");
        ss.put("phone",mobile);
        ss.put("deviceId","A-asdasdasdasdsa");
        ss.put("userIp","10.40.138.3");
        ss.put("captcha","546782");
        ss.put("content","boss you no need sb lalalal");
        String  originStr = "";
        for(String key : ss.keySet()){
            originStr += key+"="+ss.get(key)+"&";
        }
        originStr += "key=fe68e63bea35f8edeae04daec0ecb722";
        System.out.println(originStr);
        String sign = getSHA256(originStr);
        System.out.println(sign);
        ss.put("sign",sign);
        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println(JSONObject.toJSONString(ss));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(ss), headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST,request , String.class );
//        PayReturn payReturn = JSONObject.parseObject(response.getBody(),PayReturn.class);
        System.out.println(response.getBody());
        return "546782";
    }

    @Override
    public String chaxun(String tradeNo) {
        String url = "https://openapibeta.dokypay.com/trade/query";
        TreeMap<String, Object> param= new TreeMap<>();
        param.put("version","1.1");
        param.put("merchantId","devTestAppId");
        param.put("tradeNo",tradeNo);
        String originStr = "";

        for(String key : param.keySet()){
            originStr += key+"="+param.get(key)+"&";
        }
        originStr += "key=fe68e63bea35f8edeae04daec0ecb722";
        System.out.println(originStr);
        String sign = getSHA256(originStr);
        System.out.println(sign);
        param.put("sign",sign);
        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println(JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET,request , String.class );
//        PayReturn payReturn = JSONObject.parseObject(response.getBody(),PayReturn.class);
        System.out.println(response.getBody());
        return response.getBody();
    }


    @Override
    public void daifu() {
//        https://pay.upayout.com/pay/createPayoutOrder
        String url = "https://pay-test.upayout.com/pay/createPayoutOrder";
        TreeMap<String, Object> param= new TreeMap<>();
        param.put("version","1.1");
        param.put("amount","0.01");
        param.put("appId","devTestAppId");
        param.put("currency","INR");
        param.put("country","IN");
        param.put("merTransNo","CZ-2020-0000000002");
        param.put("prodName","southeast.asia.payout");
        param.put("pmId","paytm.wallet.payout");
        param.put("notifyUrl","http://test.oms.upayout.com/mobile-web/mobile/commons/withdrawNotify");
        param.put("returnUrl","www.baidu.com");
        TreeMap<String,String> map = new TreeMap<>();
        map.put("payeeMobile","7777777777");
        param.put("extInfo",map);

        String originStr = "";
        for(String key : param.keySet()){
            if("extInfo".equals(key)){
                String st = "";
                TreeMap<String,String> p = (TreeMap<String, String>) param.get(key);
                for(String sk : p.keySet()){
                    st += sk+"="+p.get(sk)+"&";
                }
                st = st.substring(0,st.length()-1);
                originStr += key+"="+ st+"&";
            }else{
                originStr += key+"="+param.get(key)+"&";
            }
        }
        originStr += "key=fe68e63bea35f8edeae04daec0ecb722";
        System.out.println(originStr);
        String sign = getSHA256(originStr);
        System.out.println(sign);
        param.put("sign",sign);
        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println(JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//        PayReturn payReturn = JSONObject.parseObject(response.getBody(),PayReturn.class);
        System.out.println(response.getBody());
    }

    @Override
    public void pay() {
        String url = "https://pay-test.upayout.com/pay/createPaymentOrder";
        TreeMap<String, Object> param= new TreeMap<>();
        param.put("version","1.1");
        param.put("amount","0.01");
        param.put("appId","devTestAppId");
        param.put("currency","INR");
        param.put("country","IN");
        param.put("merTransNo","CZ-2020-0000000002");
        param.put("prodName","southeast.asia");
        param.put("notifyUrl","http://test.oms.upayout.com/mobile-web/mobile/commons/payNotify");
        param.put("returnUrl","www.baidu.com");

        String originStr = "";

        for(String key : param.keySet()){
            originStr += key+"="+param.get(key)+"&";
        }
        originStr += "key=fe68e63bea35f8edeae04daec0ecb722";
        System.out.println(originStr);
        String sign = getSHA256(originStr);
        System.out.println(sign);
        param.put("sign",sign);

        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println(JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        PayReturn payReturn = JSONObject.parseObject(response.getBody(),PayReturn.class);
        System.out.println(response.getBody());
    }


    public static void main(String[] args) {


//        List<Integer> ll = new ArrayList<>();
//        String ss = "[100,200,300,400]";
//        ll = JSONObject.parseObject(ss,ArrayList.class);
//        ll.forEach(aLong -> {
//            System.out.println(aLong);
//        });
//        ll.add(100l);
//        ll.add(200l);
//        ll.add(300l);
//        ll.add(400l);
//        System.out.println(JSONObject.toJSONString(ll));
//        TreeMap<String,String> treeMap = new TreeMap<>();
//        treeMap.put("amount","0.01");
//        treeMap.put("createTime","2020-06-16 20:08:48");
//        treeMap.put("currency","INR");
//        treeMap.put("merTransNo","CZ-2020-0000000006");
//        treeMap.put("payerEmail","12312@qq.com");
//        treeMap.put("payerMobile","12312312");
//        treeMap.put("payerName","hhh");
//        treeMap.put("pmId","credit.in");
//        treeMap.put("processAmount","0.01");
//        treeMap.put("processCurrency","INR");
//        treeMap.put("transNo","200616120847360682");
//        treeMap.put("transStatus","success");
//        treeMap.put("updateTime","2020-06-16 20:09:35");
//        String originStr = "";
//        for(String key : treeMap.keySet()){
//            originStr += key+"="+treeMap.get(key)+"&";
//        }
//        originStr += "key=fe68e63bea35f8edeae04daec0ecb722";
//        String sign = getSHA256(originStr);
//        System.out.println(sign);
//        treeMap.put("sign","5c5b68c1a0cbec6b3859b2f4ebfb5b3025412da498d1bae78eaaa355f97c5e13");
//        System.out.println(treeMap.get("sign"));
    }
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

/**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    @Override
    public String linkPay() {
        String url = "https://linkpay.surperpay.com/trade/unifiedOrder";
        TreeMap<String, Object> param= new TreeMap<>();
        param.put("version","1.0");
        param.put("charset","UTF-8");
        param.put("spid","C1599470921199");//商户号
        param.put("spbillno","CZ-2020-202-DJDDD");
        param.put("lang","zho");
        param.put("country","IN");
        param.put("currency","INR");
        param.put("tranAmt","1");
        param.put("backUrl","http://www.baidu.com");
        param.put("notifyUrl","http://www.guguagugua.com");
        param.put("userId","100231");
        param.put("productName","chongqian");
        param.put("attach","hahaha");
        param.put("productDesc","hahaha");
        String sign = "";
        for(String key : param.keySet()){
            sign += key+"="+param.get(key)+"&";
        }
//        sign = sign.substring(0,sign.length()-1);
        sign += "key=201167383d194dcf948ca82d68e953a4";
        String cc = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
        param.put("signType","MD5");
        param.put("sign", cc);
        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println(JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
        return null;
    }

    @Override
    public String linkPayOut() {
        String url = "https://linkpay.surperpay.com/trade/unifiedOrder";
        TreeMap<String, Object> param= new TreeMap<>();
        param.put("version","1.0");
        param.put("charset","UTF-8");
        param.put("spid","C1599470921199");//商户号
        param.put("spbillno","CZ-2020-202-DJDDD");
        param.put("lang","zho");
        param.put("tranAmt","1");
        param.put("country","IN");
        param.put("currency","INR");
        param.put("acctName","chenmin");
        param.put("acctId","4444333322221111");
        param.put("acctType","NETBANKING");
        param.put("flagCard","1");
        param.put("bankCode","123");
        param.put("bankName","changsha");
        param.put("ifsc","123");
        param.put("bankBranchName","changshayueluqu");
        param.put("bankAddress","changshayueluqu");
        param.put("accountNo","123");
        param.put("notifyUrl","http://www.baidu.com");
        String sign = "";
        for(String key : param.keySet()){
            sign += key+"="+param.get(key)+"&";
        }
//        sign = sign.substring(0,sign.length()-1);
        sign += "key=201167383d194dcf948ca82d68e953a4";
        String cc = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
        param.put("signType","MD5");
        param.put("sign", cc);
        //请求
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://47.xxx.xxx.96/register/checkEmail";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println(JSONObject.toJSONString(param));
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());
        return null;
    }

    @Override
    public String aliPay() {
        // 创建扫码支付请求builder，设置请求参数
//        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
//                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
//                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
//                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
//                .setTimeoutExpress(timeoutExpress)
//                //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
//                .setGoodsDetailList(goodsDetailList);
        return null;
    }

    private static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


    @Override
    public String sendMessage() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(ACCESS_TOKEN_URL,"wxe16d6c1eb151301b", "b94fa98ec1a6f9c3a401af4a0c72b524");
        AccessTokenVo accessTokenVo = restTemplate.getForEntity(url, AccessTokenVo.class).getBody();
        String urls = String.format(SEND_MESSAGE_URL,accessTokenVo.getAccess_token());
        SendMessageParamVo param = new SendMessageParamVo();
        param.setAccess_token(accessTokenVo.getAccess_token());
        param.setTouser("o4r6C4pA_iyG3AbYkAWD34Ll6y10");

        Map<String,Map<String,String>> data = new HashMap<>();
        Map<String,String> f = new HashMap<>();
        f.put("value","10/10");
        Map<String,String> f2 = new HashMap<>();
        f2.put("value","哈哈!");
        data.put("character_string1",f);
        data.put("thing2",f2);
        param.setData(data);
        param.setTemplate_id("m4eJjellNuzetz9cfy3IFhtGaqBI-a1Bc6jQLSw7BXU");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println("---->参数："+JSONObject.toJSONString(param));
        String paramssss = JSONObject.toJSONString(param);
        HttpEntity<String> request = new HttpEntity<>(paramssss, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(urls, request , String.class );
        SendMessageResultVo sendMessageResultVo = new SendMessageResultVo();
        sendMessageResultVo = JSONObject.parseObject(response.getBody(),SendMessageResultVo.class);
        System.out.println("---->结果："+JSONObject.toJSONString(sendMessageResultVo));
        return null;
    }

    @Override
    public void delayQueueTest() {

    }
}

