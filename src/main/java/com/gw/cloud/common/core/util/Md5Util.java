package com.gw.cloud.common.core.util;

import com.gw.cloud.common.core.base.exception.ApplicationException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * MD5工具类
 *
 * @author WRQ
 * @date 2019/4/5
 * @since 1.0.0
 */
public class Md5Util {

    /**
     * 字符集：GB2312
     */
    private static final String CHARSET_TYPE_GB2312 = "GB2312";

    /**
     * 解密KEY
     */
    private static final String DECRYPT_KEY = "1q2a3z4w";

    /**
     * 解密
     *
     * @param data 密文字符串
     * @return 明文字符串
     */
    public static String decrypt(String data) {
        try {
            byte[] bytesrc = convertHexString(data);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(DECRYPT_KEY.getBytes(CHARSET_TYPE_GB2312));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(DECRYPT_KEY.getBytes(CHARSET_TYPE_GB2312));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(bytesrc);
            // 当数据库连接字符串中包含“%”时，java.net.URLDecoder.decode转换时会报错
            String result = new String(retByte);
            result = result.replace("%", "%25");
            return result;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * 加密
     *
     * @param data 明文字符串
     * @return 密文字符串
     */
    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(DECRYPT_KEY.getBytes(CHARSET_TYPE_GB2312));
            SecretKeyFactory keyFactory= SecretKeyFactory.getInstance("DES");
            SecretKey secreKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(DECRYPT_KEY.getBytes(CHARSET_TYPE_GB2312));
            cipher.init(Cipher.ENCRYPT_MODE, secreKey, iv);
            return new String(cipher.doFinal(data.getBytes(CHARSET_TYPE_GB2312)));
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    private static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }
}