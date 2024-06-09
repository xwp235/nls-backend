package com.nlsapi.core.business.service;

import com.nlsapi.core.business.req.FileTransPayReq;
import com.nlsapi.core.business.resp.OrderInfoPayResp;

public interface FileTransService {

    OrderInfoPayResp pay(FileTransPayReq req) throws Exception;

}
