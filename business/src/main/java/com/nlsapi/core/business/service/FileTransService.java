package com.nlsapi.core.business.service;

import com.nlsapi.core.business.req.FileTransPayReq;

public interface FileTransService {

    void pay(FileTransPayReq req) throws Exception;

}
