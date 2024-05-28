package com.nlsapi.core.business.service;

import com.nlsapi.core.business.entity.MastMemberEntity;
import com.nlsapi.core.business.req.web.MemberLoginReq;
import com.nlsapi.core.business.req.web.MemberRegisterReq;
import com.nlsapi.core.business.resp.MemberLoginResp;

public interface MemberService {

    MastMemberEntity getByAccount(String account);

    void register(MemberRegisterReq req);

    MemberLoginResp login(MemberLoginReq req);

}
