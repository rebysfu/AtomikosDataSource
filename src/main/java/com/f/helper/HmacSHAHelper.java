package com.f.helper;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 下午7:05
 */
public final class HmacSHAHelper {
    private static final String MAC_NAME = "HmacSHA1";

    /**
     * 使用 HMAC-SHA1 签名方法对 encryptText 进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return 返回被加密后的字符串
     * @throws Exception
     */
    public static String hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        return hmacSHA1Encrypt(encryptText.getBytes(StandardCharsets.UTF_8), encryptKey);
    }


    /**
     * 使用 HMAC-SHA1 签名方法对 encryptText 进行签名
     *
     * @param encryptData 被签名的字符串UTF-8编码数组
     * @param encryptKey  密钥
     * @return 返回被加密后的字符串
     * @throws Exception
     */
    public static String hmacSHA1Encrypt(byte[] encryptData, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(encryptData);
        StringBuilder stringBuilder = b2Hex(bytes);
        return stringBuilder.toString();
    }


    /**
     * @param bytesArray
     * @return
     */
    private static StringBuilder b2Hex(byte[] bytesArray) {
        if (bytesArray == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytesArray) {
            String hv = String.format("%02x", b);
            stringBuilder.append(hv);
        }
        return stringBuilder;
    }


    /**
     * 获取参数签名（参数按照ascii升序排列）
     *
     * @return
     * @throws Exception
     */
    public static String getEncryptText(String encryptKey, String channels, String md5, String versionCode) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("channels=" + channels + "&");
        stringBuilder.append("md5=" + md5 + "&");
        stringBuilder.append("versionCode=" + versionCode);
        return hmacSHA1Encrypt(stringBuilder.toString(), encryptKey);
    }
}
