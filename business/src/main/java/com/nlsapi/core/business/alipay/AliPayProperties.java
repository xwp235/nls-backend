package com.nlsapi.core.business.alipay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AliPayProperties {


    @Value("${alipay.gatewayHttpsHost}")
    private String gatewayHttpsHost;

    @Value("${alipay.gatewayHost}")
    private String gatewayHost;

    @Value("${alipay.appId}")
    private String appId;

    @Value("${alipay.merchantPrivateKey}")
    private String merchantPrivateKey;

    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;

    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    @Value("${alipay.encryptKey}")
    private String encryptKey;

    @Value("${alipay.returnUrl}")
    private String returnUrl;

}
