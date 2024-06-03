package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.req.FileTransPayReq;
import com.nlsapi.core.business.service.FileTransService;
import com.nlsapi.core.common.utils.LogUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/file-trans")
@RequiredArgsConstructor
public class WebFileTransController {

    private final FileTransService fileTransService;

    @PostMapping("/pay")
    public void pay(@Valid @RequestBody FileTransPayReq req) throws Exception {
        LogUtil.info("语音识别支付开始");
        fileTransService.pay(req);
        LogUtil.info("语音识别支付结束");
//        return new CommonResp<>(orderInfoPayResp);
    }

//    @GetMapping("/query")
//    public CommonResp<PageResp<FiletransQueryResp>> query(@Valid FiletransQueryReq req)  {
//        req.setMemberId(LoginMemberContext.getId());
//        PageResp<FiletransQueryResp> pageResp = filetransService.query(req);
//        return new CommonResp<>(pageResp);
//    }

}
