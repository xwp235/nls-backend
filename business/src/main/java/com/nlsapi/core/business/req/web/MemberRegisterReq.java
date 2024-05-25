package com.nlsapi.core.business.req.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRegisterReq {

    /**
     * 手机号
     */
    @NotBlank(message = "{web.sms-code.accountNotBlank}")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "{web.sms-code.passwordNotBlank}")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "{web.sms-code.verificationCodeNotBlank}")
    private String code;

}
