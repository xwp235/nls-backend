package com.nlsapi.core.common.utils;

import com.nlsapi.core.common.ErrorConstants;
import com.nlsapi.core.common.exception.SystemException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    private final static String MD5 = "MD5";

    public static String md5Hash(String input) {
        // 创建一个MessageDigest实例，指定使用MD5算法
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(ErrorConstants.ERR_1000, true,e);
        }
        // 计算哈希值
        var hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        // 将哈希值转换为16进制字符串
        var sb = new StringBuilder();
        for (var b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
