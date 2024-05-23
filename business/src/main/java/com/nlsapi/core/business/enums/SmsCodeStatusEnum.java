package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsCodeStatusEnum {

    USED("1", "已使用"),
    NOT_USED("0", "未使用");

    private final String code;

    private final String desc;

}
