package com.nlsapi.core.common.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    // 重载方法，处理char类型的分隔符
    public static List<String> splitTrim(String input, char delimiter) {
        return splitTrim(input, Character.toString(delimiter));
    }

    /**
     * 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     * @param input 输入字符串
     * @param delimiter 分隔符
     */
    public static List<String> splitTrim(String input, String delimiter) {
        // 1. 切分字符串
        var parts = input.split(delimiter);
        // 2. 用于存储最终结果的列表
        var result = new ArrayList<String>();
        // 3. 遍历切分后的字符串数组
        for (var part : parts) {
            // 4. 去除两边的空白符
            var trimmedPart = part.trim();
            // 5. 如果去除空白符后的字符串不为空，则加入结果列表
            if (!trimmedPart.isEmpty()) {
                result.add(trimmedPart);
            }
        }
        return result;
    }

}
