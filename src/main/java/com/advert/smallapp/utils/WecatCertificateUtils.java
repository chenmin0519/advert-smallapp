package com.advert.smallapp.utils;

import sun.security.x509.X509CertImpl;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import java.util.Enumeration;

/**
 * @version: V1.0
 * @author: minchen
 * @className: WecatCertificateUtils
 * @packageName: com.hydee.h3.pay.util
 * @description: 微信证书相关工具
 * @data: 2021/10/25 14:15
 **/
public class WecatCertificateUtils {

    /**
     * 解密微信平台证书
     * @param apiV3Key apiv3私钥
     * @param associatedData 获取证书返回相关数据
     * @param nonce 获取证书返回随机串
     * @param ciphertext 证书密文
     * @return
     */
    public static String decryptionCertificate(String apiV3Key,String associatedData,String nonce,String ciphertext) throws GeneralSecurityException, IOException {
        AesUtils aesUtils = new AesUtils(apiV3Key.getBytes());
        return aesUtils.decryptToString(associatedData.getBytes(),nonce.getBytes(),ciphertext);
    }

    /**
     * 生成证书io流
     * @param certificate 证书明文
     * @return
     */
    public static ByteArrayInputStream certificateGenerationFileIO(String certificate){
        return new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 通过证书流获取证书对象
     * @param inputStream 证书流
     * @return
     * @throws CertificateException
     */
    public static Certificate getCertificateFile(ByteArrayInputStream inputStream) throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        Certificate certificate = null;
        try {
            certificate = cf.generateCertificate(inputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return certificate;
    }

    /**
     * 解析证书获取序列号私钥
     * @param urlStr
     * @param passwd
     * @throws Exception
     */
    public static void parse(String urlStr, String passwd) throws Exception {
        URL url= new URL(urlStr);
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream= conn.getInputStream();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(inStream, passwd.toCharArray());
        String keyAlias = null;
        //解析证书，必须有别名
        Enumeration<String> aliases = ks.aliases();
        if (aliases.hasMoreElements()) {
            keyAlias = aliases.nextElement();
        }
        //解析私钥
        PrivateKey privateKey = (PrivateKey) ks.getKey(keyAlias, passwd.toCharArray());
        Certificate cert = ks.getCertificate(keyAlias);
        BigInteger serialNumber = ((X509CertImpl) cert).getSerialNumber();
        //证书一般都使用16进制表示
        String certSn = serialNumber.toString(16);
        String prikeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        //设置证书序列号和私钥
        System.out.println(certSn.length());
        System.out.println(certSn);
        System.out.println(prikeyStr);
    }

    public static void main(String[] args)  throws Exception {
        String passwd = "1628148826";
        InputStream inStream= new FileInputStream(new File("C://Users/min/Desktop/jixin/商户号配置/1628148826/1628148826_20221024_cert/apiclient_cert.p12"));
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(inStream, passwd.toCharArray());
        String keyAlias = null;
        //解析证书，必须有别名
        Enumeration<String> aliases = ks.aliases();
        if (aliases.hasMoreElements()) {
            keyAlias = aliases.nextElement();
        }
        //解析私钥
        PrivateKey privateKey = (PrivateKey) ks.getKey(keyAlias, passwd.toCharArray());
        Certificate cert = ks.getCertificate(keyAlias);
        BigInteger serialNumber = ((X509CertImpl) cert).getSerialNumber();
        //证书一般都使用16进制表示
        String certSn = serialNumber.toString(16);
        String prikeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        //设置证书序列号和私钥
        System.out.println(certSn.length());
        System.out.println(certSn);
        System.out.println(prikeyStr);
    }
}
