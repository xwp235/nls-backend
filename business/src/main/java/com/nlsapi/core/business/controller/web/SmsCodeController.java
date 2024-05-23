package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.enums.SmsCodeUsageEnum;
import com.nlsapi.core.business.req.web.SmsCodeRegisterReq;
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

    @PostMapping("send-for-register")
    public JsonResp sendMobileSmsCodeForRegister(@Valid @RequestBody SmsCodeRegisterReq req) {
          smsCodeService.sendCode(req.getAccount(), SmsCodeUsageEnum.REGISTER);
          return JsonResp.ok();
    }

}
