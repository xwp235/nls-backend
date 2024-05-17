package com.nlsapi.core.common.aspect;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class LogData {

    private Long logId;
    private String device;
    private String protocol;
    private String ip;
    private Integer remotePort;
    private Integer serverPort;
    private String path;
    private String referer;
    private String method;
    private String userAgent;
    private String headers;
    private String parameters;
    private String username;
    private Integer status;
    private String failReason;
    private String responseTime;
    private ZonedDateTime createTime;

}
