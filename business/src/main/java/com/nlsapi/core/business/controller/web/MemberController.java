package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.req.web.MemberRegisterReq;
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

    @PostMapping("register")
    public JsonResp sendForRegister(@Valid @RequestBody MemberRegisterReq req) {
      req.setPassword(CryptoUtil.md5Hash(req.getPassword()));
      memberService.register(req);
      return JsonResp.ok();
    }

}
