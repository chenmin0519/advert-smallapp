package com.advert.smallapp.utils;

import com.advert.smallapp.constant.Constants;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.pojo.SendTitleRTime;
import com.advert.smallapp.tdo.FileVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

@Slf4j
public class DahuZhongxueFileUtils {

    final static String FILE_LIST_URL = "http://hn.lyedu.com.cn/obpm/runtime/11de-f053-df18d577-aeb6-19a7865cfdb6/views/__KpPcM0j2gjpjb4WbLa9/documents?parentId=&sortCol=&sortStatus=&_currpage=1&lines=15&treedocid=&parentNodeId=&_docid=&_fieldid=&isRelate=&startDate=&endDate=&parentParam=&isQueryButton=false&newTime=1663245585423&_=0.1566335321602048";

    final static String FILE_DETAIL_URL = "http://hn.lyedu.com.cn/obpm/runtime/11de-f053-df18d577-aeb6-19a7865cfdb6/forms/__pj3p3kS3UtFeoMkz8EB/documents/";
    final static String FILE_DETAIL_URL_PARAMS = "?appId=11de-f053-df18d577-aeb6-19a7865cfdb6&_=0.7457669887220097&parentId=&isRelate=undefined&opentarget=detail";

    final static String AOUTH_URL = "http://hn.lyedu.com.cn/api/openapi-uc/oauth/token?client_id=880bc85249a0406fbff8ca0f114f2e51&client_secret=d12acfcbea4f9631&grant_type=password&password=eba6azTGTJlJmAQjEOou3Q==&username=dhzx";

    /**
     * 获取文件列表
     * @return
     */
    public static String getFileList(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Cookie",Constants.cookie);
        headers.add("Host","http://hn.lyedu.com.cn");
        headers.add("Referer","http://hn.lyedu.com.cn/obpm/portal/good/html/view.html?appId=11de-f053-df18d577-aeb6-19a7865cfdb6&viewId=__KpPcM0j2gjpjb4WbLa9&opentarget=detail&containTitle=%E6%96%87%E4%BB%B6%E6%8E%A5%E6%94%B6");
        Map<String,Object> params = new HashMap<>();
        params.put("appId","11de-f053-df18d577-aeb6-19a7865cfdb6");
        params.put("viewId","__KpPcM0j2gjpjb4WbLa9");
        params.put("opentarget","detail");
        params.put("containTitle","文件接收");
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(params), headers);
        ResponseEntity<String> response = restTemplate.exchange( FILE_LIST_URL, HttpMethod.POST, request , String.class );
        String body = response.getBody();
        return body;
    }

    public static void main(String[] args) throws ParseException {
        checkDhzxFile();
    }
    public static void checkDhzxFile() throws ParseException {
        try {
            List<SendTitleRTime> dayTime = new ArrayList<>();
            refrushToken();
            Date afterDate = Constants.timeTemp;
            String listInfo = getFileList();
            if (listInfo != null) {
                JSONObject jsonObject = JSONObject.parseObject(listInfo);
                if (jsonObject.get("errmsg").toString().equals("ok")) {
                    JSONObject dataJson = jsonObject.getJSONObject("data");
                    JSONArray dataList = dataJson.getJSONArray("data");
                    for (int i = 0; i < dataList.size(); i++) {
                        JSONObject item = dataList.getJSONObject(i);
                        String sendTitle = item.getJSONObject("items").getJSONObject("__2y7BLXo25ZJMCkGpcSk").get("value").toString();
                        String date = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("value").toString();
                        String docId = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("docId").toString();
                        String formId = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("formId").toString();
                        Date fileDate = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
                        if (Constants.timeTemp == null) {
                            Long time = System.currentTimeMillis();  //当前时间的时间戳
                            long zero = time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
                            Constants.timeTemp = new Date(zero);//今天零点零分零秒
                            afterDate = Constants.timeTemp;
                        }
                        if (fileDate.after(Constants.timeTemp)) {
                            SendTitleRTime sendTitleRTime = new SendTitleRTime();
                            sendTitleRTime.setTime(date);
                            sendTitleRTime.setTitle(sendTitle);
                            dayTime.add(sendTitleRTime);
                            if (fileDate.after(afterDate)) {
                                afterDate = fileDate;
                            }
                            //今天的文件
                            String detail = validateDetail(docId, formId);
                            if (detail != null) {
                                JSONObject detailJsonObj = JSONObject.parseObject(detail);
                                if (detailJsonObj.get("errmsg").toString().equals("ok")) {
                                    JSONObject detailJson = detailJsonObj.getJSONObject("data");
                                    JSONArray fileds = detailJson.getJSONArray("fields");
                                    String title = "";
                                    List<FileVo> fileVos = new ArrayList<>();
                                    for (int index = 0; index < fileds.size(); index++) {
                                        if (fileds.getJSONObject(index).get("name").toString().equals("标题")) {
                                            title = fileds.getJSONObject(index).getString("value");
                                        }
                                        if (fileds.getJSONObject(index).get("name").toString().equals("附件")) {
                                            JSONArray jsonArray = JSONObject.parseArray(fileds.getJSONObject(index).getString("value"));
                                            for (int j = 0; j < jsonArray.size(); j++) {
                                                FileVo fileVo = new FileVo();
                                                String fileName = jsonArray.getJSONObject(j).getString("name");
                                                String filePath = jsonArray.getJSONObject(j).getString("path");
                                                String userName = jsonArray.getJSONObject(j).getString("userName");
                                                String time = jsonArray.getJSONObject(j).getString("time");
                                                fileVo.setTitle(title);
                                                fileVo.setFilePath(filePath);
                                                fileVo.setFileName(fileName);
                                                fileVo.setUserName(userName);
                                                fileVo.setTime(time);
                                                fileVos.add(fileVo);
                                            }
                                        }
                                    }
                                    //发送消息
                                    sendMesagger(fileVos);
                                }
                            }

                        }
                    }
                }
            }
            //时间节点后推
            Constants.timeTemp = afterDate;
            String context = "检查新文件完成：\n";
            if(dayTime != null && dayTime.size() > 0){
                for(SendTitleRTime sendTitleRTime : dayTime){
                    context += "通知标题："+sendTitleRTime.getTitle() + ";\n";
                    context += "通知时间："+sendTitleRTime.getTime() + ";\n\n";
                }
            }else{
                context += "无新文件通知。";
            }
            sendMessage(context);
            sendMessageQW(context);
        }catch (Exception e){
            sendMessage("检查新文件存在报错：\n" + e.getMessage());
        }
    }

    private static void refrushToken() {
        RestTemplate restTemplate = new RestTemplate();
        String userName = "dhzx";
        String password = "eba6azTGTJlJmAQjEOou3Q==";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Access-Token","c2f68c274dbe603a8f583732aae2229d");
        headers.set("Branch_Code","E000078");
        headers.set("Current-Org-Id","E000078200000000375");
        headers.set("Tenant-Id","E000078");
        headers.set("X-Client-Id","880bc85249a0406fbff8ca0f114f2e51");
        headers.set("X-User-Account","E000078200000042568");
        Map<String,Object> param = new HashMap<>();
//        param.put("username",userName);
//        param.put("password",password);
//        param.put("client_id","880bc85249a0406fbff8ca0f114f2e51");
//        param.put("client_secret","d12acfcbea4f9631");
//        param.put("grant_type","password");
        String url = AOUTH_URL;
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, request , String.class );
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String token = jsonObject.getString("access_token");
        Integer expiresIn = jsonObject.getInteger("expires_in");
        String accessToken = getToken(token,expiresIn);
//        String cookie = response.getHeaders().get("Set-Cookie").get(0).split(";")[0] + "; authorized=true; accessToken=" + accessToken;
//        Constants.cookie = cookie;

        String getAccreditCodeUrl = "http://hn.lyedu.com.cn/api/openapi-uc/accreditcode/getAccreditCode";
        headers.add("Cookie","authorized=true");
        headers.set("Access-Token",token);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        param.put("accessToken",token);
        param.put("userId","E000078200000042568");
        HttpEntity<String> accreditCodeRequest = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> accreditCodeResponese = restTemplate.exchange( getAccreditCodeUrl, HttpMethod.POST, accreditCodeRequest , String.class );
        JSONObject accreditCodeJson = JSONObject.parseObject(accreditCodeResponese.getBody());
        String accreditCode = accreditCodeJson.getJSONObject("responseEntity").getString("accreditCode");

        String loginEcoqrcodeUrl = "http://hn.lyedu.com.cn/signon/runtime/login/ecoqrcodelogin/user/ssi?token="
                + token
                + "&=access_token="
                + token
                + "&accredit_code="
                + accreditCode;

        HttpHeaders loginheaders = new HttpHeaders();
        loginheaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        loginheaders.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        loginheaders.add("Accept-Encoding","gzip,deflate");
        loginheaders.add("Accept-language","zh-CN.zh;q=0.9");
        loginheaders.add("Cookie","authorized=true");
        loginheaders.add("Host","http://hn.lyedu.com.cn");
        loginheaders.add("Upgrade-Insecure-Requests","1");
        loginheaders.add("Connection","keep-alive");
        HttpEntity<String> loginrequest = new HttpEntity<>(null, headers);
        RestTemplate restTemplateNoRedirct = new RestTemplate(new NoRedirectSimpleClientHttpRequestFactory());
        ResponseEntity<String> loginresponse = restTemplateNoRedirct.exchange( loginEcoqrcodeUrl, HttpMethod.GET, loginrequest , String.class );
        String accessTokenCookie = loginresponse.getHeaders().get("Set-Cookie").get(0);
        Constants.cookie = accessTokenCookie;
        //        response.get("Set-Cookie").get(0);
//        String[] split = s.split(";");
//        String sessionId = "";


    }
    public static class NoRedirectSimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

        @Override
        protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
            super.prepareConnection(connection, httpMethod);
            connection.setInstanceFollowRedirects(false);
        }
    }

    private static String getToken(String token, Integer expiresIn){
        Date signDate = new Date();
        Algorithm algorithm = Algorithm.HMAC256(token);//进行加密算法
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put( "typ","JWT");
        headerMap.put( "alg","HS256");
        return JWT.create()
                //(token的Header信息)
                .withHeader(headerMap)
                //设置当前签发时间(token的Payload信息)
//                .withIssuedAt(signDate)
                //设置token过期时间(token的Payload信息)
                .withExpiresAt(signDate)
                //自定义存放用户id在tokne中(token的自定义Payload信息)
                .withClaim("iss", "auth0")
                //自定义存放用户名在token中(token的自定义Payload信息)
                .withClaim("username","__8TRPf3sknaOyxQCOSeK")
//                .withClaim("exp",date.getTime()/1000)
                //(token的Signature信息)
                .sign(algorithm);
    }

    private static String validateDetail(String docId, String formId) {
        Long newTime = new Date().getTime();
        String url = FILE_DETAIL_URL+ docId + FILE_DETAIL_URL_PARAMS + "&docid="+docId+"&formId="+formId + "&newTime="+newTime;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Cookie",Constants.cookie);
        headers.add("Host","http://hn.lyedu.com.cn");
//        headers.add("Referer","http://hn.lyedu.com.cn/obpm/portal/good/html/view.html?appId=11de-f053-df18d577-aeb6-19a7865cfdb6&viewId=__KpPcM0j2gjpjb4WbLa9&opentarget=detail&containTitle=%E6%96%87%E4%BB%B6%E6%8E%A5%E6%94%B6");
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET, request , String.class );
        String body = response.getBody();
        return body;
    }

    /**
     * 发送企业微信消息
     * @param fileVos
     */
    static String robot_url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=1fb1effb-4290-4b9a-b843-67443da6df26";
    private static void sendMessageQW(String context) {
        RestTemplate restTemplate = new RestTemplate();
        String contextStr = "通知：";
        contextStr += context;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> param = new HashMap<>();
        param.put("msgtype","text");
        Map<String,Object> text = new HashMap<>();
        text.put("content",contextStr);
        List<String> users = new ArrayList<String>();
        users.add("@all");
        text.put("mentioned_list",users);
        param.put("text",text);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( robot_url, HttpMethod.POST, request , String.class );
    }
    private static void sendMesagger(List<FileVo> fileVos) {
        log.info("发送消息："+JSONObject.toJSONString(fileVos));
        String contextStr = "新文件通知：\n";
        RestTemplate restTemplate = new RestTemplate();
        for(FileVo v : fileVos){
            String fileuri = "http://hn.lyedu.com.cn/obpm/runtime/file/download?filename="+v.getFileName()+"&filepath="+v.getFilePath();
            contextStr += "\n" +
                    "标题："+v.getFileName() +"\n" +
                    "发布人："+v.getUserName()+"\n" +
                    "发布时间："+v.getTime()+"\n" +
                    "备注：复制文件下载地址内容在浏览器打开，可能需要登录，登录之后可复制下载地址直接下载。\n\n" +
                    "文件下载地址："+ fileuri + "\n";
        }

        HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            Map<String,Object> param = new HashMap<>();
            param.put("msgtype","text");
            Map<String,Object> text = new HashMap<>();
            text.put("content",contextStr);
            List<String> users = new ArrayList<String>();
            users.add("@all");
            text.put("mentioned_list",users);
            param.put("text",text);
            HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
            ResponseEntity<String> response = restTemplate.exchange( robot_url, HttpMethod.POST, request , String.class );
    }

    private static void sendMessageDingDing(List<FileVo> fileVos){
        String contextStr = "新文件通知：\n";
        RestTemplate restTemplate = new RestTemplate();
        for(FileVo v : fileVos){
            String fileuri = "http://hn.lyedu.com.cn/obpm/runtime/file/download?filename="+v.getFileName()+"&filepath="+v.getFilePath();
            contextStr += "\n" +
                    "标题："+v.getFileName() +"\n" +
                    "发布人："+v.getUserName()+"\n" +
                    "发布时间："+v.getTime()+"\n" +
                    "备注：复制文件下载地址内容在浏览器打开，可能需要登录，登录之后可复制下载地址直接下载。\n\n" +
                    "文件下载地址："+ fileuri + "\n";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> param = new HashMap<>();
        param.put("msgtype","text");
        Map<String,String> text = new HashMap<>();
        text.put("content",contextStr);
        param.put("text",text);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( dingding_robot, HttpMethod.POST, request , String.class );
    }

    static String dingding_robot = "https://oapi.dingtalk.com/robot/send?access_token=cc1a022546457d393aa3d5af9bc1bd1998a454978b2e8ebf86bf688b08dc587c";
    private static void sendMessage(String context){
        RestTemplate restTemplate = new RestTemplate();
        String contextStr = "通知：";
        contextStr += context;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> param = new HashMap<>();
        param.put("msgtype","text");
        Map<String,String> text = new HashMap<>();
        text.put("content",contextStr);
        param.put("text",text);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
        ResponseEntity<String> response = restTemplate.exchange( dingding_robot, HttpMethod.POST, request , String.class );
    }

}
