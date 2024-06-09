package com.nlsapi.core.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderInfoChannelEnum {

    ALI_PAY("A", "支付宝"),
    WX_PAY("W", "微信");

    private final String code;

    private final String desc;

}
