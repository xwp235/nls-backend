package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderInfoTypeEnum {

    FILE_TRANS_PAY("1", "语音识别单次付费"),
    ;

    private final String code;

    private final String desc;

}
