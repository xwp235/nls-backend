package com.nlsapi.core.business.enums;

import lombok.Getter;

@Getter
public enum FileTransPayStatusEnum {
    I("I", "未支付"),
    P("P", "处理中"),
    S("S", "支付成功"),
    F("F", "支付失败");

    private final String code;

    private final String desc;

    FileTransPayStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
