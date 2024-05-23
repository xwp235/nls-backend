package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsCodeUsageEnum {

    REGISTER("0", "注册"),
    RESET("1", "重置密码");

    private final String code;

    private final String desc;

}
