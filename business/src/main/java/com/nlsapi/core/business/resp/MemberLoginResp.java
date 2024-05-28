package com.nlsapi.core.business.resp;

import lombok.Data;

@Data
public class MemberLoginResp {

    private Long id;
    private String nickname;
    private String token;

}
