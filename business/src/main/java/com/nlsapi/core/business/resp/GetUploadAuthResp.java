package com.nlsapi.core.business.resp;

import lombok.Data;

@Data
public class GetUploadAuthResp {

    private String uploadAuth;

    private String uploadAddress;

    private String videoId;

}
