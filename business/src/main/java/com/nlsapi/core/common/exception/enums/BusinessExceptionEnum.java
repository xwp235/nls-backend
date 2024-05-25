package com.nlsapi.core.common.exception.enums;

import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.ErrorConstants;
import com.nlsapi.core.common.utils.MessageUtil;

public enum BusinessExceptionEnum implements IBusinessException {

    INVALID_REQUEST_PARAMETER(AppConstants.EXCEPTION_TYPE.WARN, ErrorConstants.ERR_10000,false,MessageUtil.getMessage("invalidRequestParameters")),
    OBJ_SERIALIZE_2_JSON_FAILED(AppConstants.EXCEPTION_TYPE.ERROR, ErrorConstants.ERR_10001,true,MessageUtil.getMessage("objSerialize2JsonFailed")),
    JSON_DESERIALIZE_2_OBJ_FAILED(AppConstants.EXCEPTION_TYPE.ERROR, ErrorConstants.ERR_10002, true,MessageUtil.getMessage("jsonDeserialize2objFailed")),
    DEEP_COPY_OBJ_FAILED(AppConstants.EXCEPTION_TYPE.ERROR, ErrorConstants.ERR_10003, true,MessageUtil.getMessage("deepCopyObjFailed"));

    BusinessExceptionEnum(String type,int code,boolean shouldLog,String message) {
        this.type = type;
        this.code = code;
        this.shouldLog = shouldLog;
        this.message = message;
    }

    private final String type;
    private final int code;
    private final boolean shouldLog;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean shouldLog() {
        return shouldLog;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
