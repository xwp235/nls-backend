package com.nlsapi.core.common.exception.enums;

import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum implements IBusinessException {

    INVALID_REQUEST_PARAMETER(AppConstants.EXCEPTION_TYPE.WARN,10000,MessageUtil.getMessage("invalidRequestParameters")),
    OBJ_SERIALIZE_2_JSON_FAILED(AppConstants.EXCEPTION_TYPE.ERROR,10001,MessageUtil.getMessage("objSerialize2JsonFailed")),
    JSON_DESERIALIZE_2_OBJ_FAILED(AppConstants.EXCEPTION_TYPE.ERROR, 10002, MessageUtil.getMessage("jsonDeserialize2objFailed")),
    DEEP_COPY_OBJ_FAILED(AppConstants.EXCEPTION_TYPE.ERROR, 10003, MessageUtil.getMessage("deepCopyObjFailed"));

    private final String type;
    private final int code;
    private final String message;

}
