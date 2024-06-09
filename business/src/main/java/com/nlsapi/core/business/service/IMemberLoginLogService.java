package com.nlsapi.core.business.service;

import com.nlsapi.core.business.resp.MemberLoginResp;

public interface IMemberLoginLogService {

    void save(MemberLoginResp resp);

    void updateHeartInfo();

}
