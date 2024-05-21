package com.nlsapi.core.common.exception.enums;

import com.nlsapi.core.common.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemExceptionEnum {

    SERVER_INTERNAL_ERROR("ERROR", 500, MessageUtil.getMessage("serverInternalError"));

    private final String type;
    private final int code;
    private final String message;

}
