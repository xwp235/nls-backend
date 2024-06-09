package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderInfoStatusEnum {

    I("I", "未支付"),
    P("P", "处理中"),
    S("S", "支付成功"),
    F("F", "支付失败");

    private final String code;
    private final String desc;

}
