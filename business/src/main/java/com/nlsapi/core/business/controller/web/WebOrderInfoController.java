package com.nlsapi.core.business.controller.web;

import com.nlsapi.core.business.service.OrderInfoService;
import com.nlsapi.core.common.resp.JsonResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/order-info")
@RequiredArgsConstructor
public class WebOrderInfoController {

    private final OrderInfoService orderInfoService;

    @GetMapping("/query-order-status/{orderNo}")
    public JsonResp sendForRegister(@PathVariable String orderNo) {
        String status = orderInfoService.queryOrderStatus(orderNo);
        return JsonResp.ok().setData(status);
    }
}
