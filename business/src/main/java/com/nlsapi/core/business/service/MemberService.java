package com.nlsapi.core.business.service;

import com.nlsapi.core.business.entity.MastMemberEntity;
import com.nlsapi.core.business.req.web.MemberRegisterReq;

public interface MemberService {

    MastMemberEntity getByAccount(String account);

    void register(MemberRegisterReq req);

}