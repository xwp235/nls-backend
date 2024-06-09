package com.nlsapi.core.business.controller.web;

import cn.hutool.crypto.digest.DigestUtil;
import com.nlsapi.core.business.req.web.MemberLoginReq;
import com.nlsapi.core.business.req.web.MemberRegisterReq;
import com.nlsapi.core.business.req.web.MemberResetReq;
import com.nlsapi.core.business.resp.MemberLoginResp;
import com.nlsapi.core.business.service.IMemberLoginLogService;
import com.nlsapi.core.business.service.MemberService;
import com.nlsapi.core.common.resp.JsonResp;
import com.nlsapi.core.common.utils.CryptoUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("web/member")
public class MemberController {

    private final MemberService memberService;
    private final IMemberLoginLogService memberLoginLogService;
//    private final SmsCodeService smsCodeService;

    @PostMapping("register")
    public JsonResp register(@Valid @RequestBody MemberRegisterReq req) {
      req.setPassword(CryptoUtil.md5Hash(req.getPassword()));
//        log.info("会员注册开始：{}", req.getMobile());

//      smsCodeService.validCode(req.getAccount(), SmsCodeUsageEnum.REGISTER,req.getCode());
//        log.info("注册验证码校验通过：{}", req.getMobile());
      memberService.register(req);

      return JsonResp.ok();
    }

    @PostMapping("login")
    public MemberLoginResp login(@Valid @RequestBody MemberLoginReq req) {
        req.setPassword(CryptoUtil.md5Hash(req.getPassword().toLowerCase()));
//        log.info("会员登录开始：{}", req.getMobile());
        // 校验图片验证码，防止机器人
//        kaptchaService.validCode(req.getImageCode(), req.getImageCodeToken());

        return memberService.login(req);

    }

    @PostMapping("/reset")
    public void reset(@Valid @RequestBody MemberResetReq req) {
        req.setPassword(DigestUtil.md5Hex(req.getPassword().toLowerCase()));
//        log.info("会员忘记密码开始：{}", req.getMobile());
//        smsCodeService.validCode(req.getMobile(), SmsCodeUseEnum.RESET.getCode(), req.getCode());
//        log.info("忘记密码验证码校验通过：{}", req.getMobile());
        memberService.reset(req);
    }

    @GetMapping("/heart")
    public void heart() {
        memberLoginLogService.updateHeartInfo();
    }


}
