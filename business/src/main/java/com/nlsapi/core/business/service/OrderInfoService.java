package com.nlsapi.core.business.service;

import com.nlsapi.core.business.req.OrderInfoPayReq;
import com.nlsapi.core.business.resp.OrderInfoPayResp;

public interface OrderInfoService {

    OrderInfoPayResp pay(OrderInfoPayReq req);

    String queryOrderStatus(String orderNo);

}
