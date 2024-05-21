package com.nlsapi.core.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdWorkerUtil {

    private static Snowflake getDefaultSnowflake() {
        return IdUtil.getSnowflake(1,1);
    }

    public static long getId() {
        return getDefaultSnowflake().nextId();
    }

    public static String getIdStr() {
        return getDefaultSnowflake().nextIdStr();
    }

}
