package com.advert.smallapp.utils;

import com.advert.smallapp.tdo.WechatOpenidDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
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

    public JSONObject decryptData(String encryptedData, String sessionKey, String iv) throws Exception {
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decodeBase64(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return (JSONObject) JSONObject.parse(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
