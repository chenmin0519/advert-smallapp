package com.advert.smallapp.utils;

import com.advert.smallapp.tdo.WechatOpenidDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@ConfigurationProperties("wechat.mini")
@Validated
@Data
@Slf4j
public class WechatMiniClient {

    private String defaultAppid;

    private Appinfo defaulApp;

    private static String api = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code" +
            "=%s&grant_type=authorization_code";

    private RestTemplate restTemplate = new RestTemplate();
    /**
     * 小程序通过code换取openid
     *
     * @param code
     * @return
     */
    public WechatOpenidDTO jscode2session(String code, String appid) throws Exception {


        //带参数请求微信授权
        WechatOpenidDTO forObject = restTemplate.getForObject(String.format(api, "wx5ab86e4384eecfdd", "e276d385be00b58203f812390ddc3133", code),
                WechatOpenidDTO.class);

        if (Objects.isNull(forObject.getErrcode()) ||
                Long.valueOf(0).equals(forObject.getErrcode()))
            return forObject;

        throw new Exception("获取微信信息失败：" + forObject.getErrmsg());
    }

    @PostConstruct
    public void post() throws Exception {
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());

//        if (StringUtils.isNotBlank(defaultAppid)) {
//            if (apps.containsKey(defaultAppid)) {
//                defaulApp = apps.get(defaultAppid);
//                log.info("配置默认appid为:" + defaulApp);
//            } else {
//                throw new Exception("apps not contain defaulAppid :" + defaultAppid);
//            }
//        } else {
//            defaulApp = apps.entrySet().stream().findFirst().get().getValue();
//        }

    }

    @Data
    public static class Appinfo {

        /**
         * The ID of the info (the same as its map key by default).
         */
        @NotNull
        private String appid;

        @NotNull
        private String secret;
    }

    static class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);//
        }
    }
}
