package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypeEnum {

    MOBILE("1", "手机"),
    EMAIL("2", "邮箱");

    private final String code;

    private final String desc;

}
