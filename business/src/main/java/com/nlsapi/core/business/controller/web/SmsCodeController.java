package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.req.web.SmsCodeRegisterReq;
import com.nlsapi.core.business.req.web.SmsCodeResetReq;
import com.nlsapi.core.business.service.SmsCodeService;
import com.nlsapi.core.common.resp.JsonResp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/sms-code")
public class SmsCodeController {

    private final SmsCodeService smsCodeService;
//    private final KaptchaService kaptchaService;

    @PostMapping("send-for-register")
    public JsonResp sendForRegister(@Valid @RequestBody SmsCodeRegisterReq req) {
        // 校验图片验证码，防止短信攻击，不加的话，只能防止同一手机攻击，加上图片验证码，可防止不同的手机号攻击
//        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

          smsCodeService.sendForRegister(req.getAccount());
          return JsonResp.ok();
    }

    @PostMapping("send-for-reset")
    public JsonResp sendForReset(@Valid @RequestBody SmsCodeResetReq req) {
        // 校验图片验证码，防止短信攻击，不加的话，只能防止同一手机攻击，加上图片验证码，可防止不同的手机号攻击
//        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        smsCodeService.sendForReset(req.getAccount());
        return JsonResp.ok();
    }

}
