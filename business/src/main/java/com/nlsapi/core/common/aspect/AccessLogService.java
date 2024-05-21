package com.nlsapi.core.common.aspect;

import com.nlsapi.core.common.utils.LogUtil;

public interface AccessLogService {

    default void save(LogData log) {
        LogUtil.info("{}",log);
    }

}
