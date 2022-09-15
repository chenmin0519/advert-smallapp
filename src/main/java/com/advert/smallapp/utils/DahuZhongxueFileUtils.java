package com.advert.smallapp.utils;

import com.advert.smallapp.constant.Constants;
import com.advert.smallapp.pojo.Context;
import com.advert.smallapp.tdo.FileVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

public class DahuZhongxueFileUtils {

    final static String FILE_LIST_URL = "http://hn.lyedu.com.cn/obpm/runtime/11de-f053-df18d577-aeb6-19a7865cfdb6/views/__KpPcM0j2gjpjb4WbLa9/documents?parentId=&sortCol=&sortStatus=&_currpage=1&lines=15&treedocid=&parentNodeId=&_docid=&_fieldid=&isRelate=&startDate=&endDate=&parentParam=&isQueryButton=false&newTime=1663245585423&_=0.1566335321602048";

    final static String FILE_DETAIL_URL = "http://hn.lyedu.com.cn/obpm/runtime/11de-f053-df18d577-aeb6-19a7865cfdb6/forms/__pj3p3kS3UtFeoMkz8EB/documents/__jUiweqTrvRDjcR1C208?newTime=1663247682057&_=0.7457669887220097&appId=11de-f053-df18d577-aeb6-19a7865cfdb6&parentId=&isRelate=undefined&opentarget=detail";


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
        Date afterDate = Constants.timeTemp;
        String listInfo = getFileList();
        if(listInfo != null){
            JSONObject jsonObject = JSONObject.parseObject(listInfo);
            if(jsonObject.get("errmsg").toString().equals("ok")){
                JSONObject dataJson = jsonObject.getJSONObject("data");
                JSONArray dataList = dataJson.getJSONArray("data");
                for( int i = 0 ; i < dataList.size() ; i ++ ){
                    JSONObject item = dataList.getJSONObject(i);
                    String date = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("value").toString();
                    String docId = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("docId").toString();
                    String formId = item.getJSONObject("items").getJSONObject("__9GvtcetKYstxYbiaC2E").get("formId").toString();
                    Date fileDate = DateUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss");
                    if(Constants.timeTemp == null){
                        Long  time = System.currentTimeMillis();  //当前时间的时间戳
                        long zero = time/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
                        Constants.timeTemp = new Date(zero);//今天零点零分零秒
                    }
                    if(fileDate.after(Constants.timeTemp)){
                        if(fileDate.after(afterDate)){
                            afterDate = fileDate;
                        }
                        //今天的文件
                        String detail = validateDetail(docId,formId);
                        if(detail != null) {
                            JSONObject detailJsonObj = JSONObject.parseObject(detail);
                            if(detailJsonObj.get("errmsg").toString().equals("ok")) {
                                JSONObject detailJson = detailJsonObj.getJSONObject("data");
                                JSONArray fileds = detailJson.getJSONArray("fields");
                                String title = "";
                                List<FileVo> fileVos = new ArrayList<>();
                                for( int index = 0 ; index < fileds.size() ; index ++ ){
                                    if(fileds.getJSONObject(index).get("name").toString().equals("标题")){
                                        title = fileds.getJSONObject(index).getString("value");
                                    }
                                    if(fileds.getJSONObject(index).get("name").toString().equals("附件")){
                                        JSONArray jsonArray = JSONObject.parseArray(fileds.getJSONObject(index).getString("value"));
                                        for( int j = 0 ; j < jsonArray.size() ; j ++ ) {
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
    }

    private static String validateDetail(String docId, String formId) {
        String url = FILE_DETAIL_URL + "&docid="+docId+"&formId="+formId;
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
    static String robot_url = "https://oapi.dingtalk.com/robot/send?access_token=cc1a022546457d393aa3d5af9bc1bd1998a454978b2e8ebf86bf688b08dc587c";
    private static void sendMesagger(List<FileVo> fileVos) {
        fileVos.stream().forEach(v ->{
            String fileuri = "http://hn.lyedu.com.cn/obpm/runtime/file/download?filename="+v.getFileName()+"&filepath="+v.getFilePath();
            RestTemplate restTemplate = new RestTemplate();
            String contextStr = "新文件通知：\n" +
                    "标题："+v.getFileName() +"\n" +
                    "发布人："+v.getUserName()+"\n" +
                    "发布时间："+v.getTime()+"\n" +
                    "文件下载地址："+ fileuri;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            Map<String,Object> param = new HashMap<>();
            param.put("msgtype","text");
            Map<String,String> text = new HashMap<>();
            text.put("content",contextStr);
            param.put("text",text);
            HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(param), headers);
            ResponseEntity<String> response = restTemplate.exchange( robot_url, HttpMethod.POST, request , String.class );
        });
    }

}
