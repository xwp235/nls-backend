package com.nlsapi.core.business.req.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsCodeResetReq {

    @NotBlank(message = "{web.sms-code.accountNotBlank}")
    private String account;

//    /**
//     * 验证码
//     */
//    @NotBlank(message = "{web.sms-code.imageCodeNotBlank}")
//    private String imageCode;
//
//    /**
//     * 图片验证码token
//     */
//    @NotBlank(message = "{web.sms-code.imageCodeTokenNotBlank}")
//    private String imageCodeToken;

}
