package com.advert.smallapp.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @version: V1.0
 * @author: minchen
 * @className: AesUtils
 * @packageName: com.hydee.h3.pay.util
 * @description: Aes解密
 * @data: 2021/10/25 14:10
 **/
public class AesUtils {
    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;
    private final byte[] aesKey;

    public AesUtils(byte[] key) {
        if (key.length != KEY_LENGTH_BYTE) {
            throw new IllegalArgumentException("无效的ApiV3Key，长度必须为32个字节");
        }
        this.aesKey = key;
    }

    /**
     * 解密
     * @param associatedData 获取证书返回相关数据
     * @param nonce 获取证书返回随机串
     * @param ciphertext 证书密文
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext)
            throws GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
