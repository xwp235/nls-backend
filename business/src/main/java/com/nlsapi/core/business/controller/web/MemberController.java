package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.req.web.MemberLoginReq;
import com.nlsapi.core.business.req.web.MemberRegisterReq;
import com.nlsapi.core.business.resp.MemberLoginResp;
import com.nlsapi.core.business.service.MemberService;
import com.nlsapi.core.common.resp.JsonResp;
import com.nlsapi.core.common.utils.CryptoUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/member")
public class MemberController {

    private final MemberService memberService;
//    private final SmsCodeService smsCodeService;

    @PostMapping("register")
    public JsonResp register(@Valid @RequestBody MemberRegisterReq req) {
      req.setPassword(CryptoUtil.md5Hash(req.getPassword()));

//      smsCodeService.validCode(req.getAccount(), SmsCodeUsageEnum.REGISTER,req.getCode());

      memberService.register(req);

      return JsonResp.ok();
    }

    @PostMapping("login")
    public MemberLoginResp login(@Valid @RequestBody MemberLoginReq req) {
        req.setPassword(CryptoUtil.md5Hash(req.getPassword().toLowerCase()));
        // 校验图片验证码，防止机器人
//        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        return memberService.login(req);

    }

}
