package com.nlsapi.core.business.enums.exception;

import com.nlsapi.core.business.CustomErrorConstants;
import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.exception.enums.IBusinessException;
import com.nlsapi.core.common.utils.MessageUtil;

public enum SmsCodeExceptionEnum implements IBusinessException {

    TOO_MANY_REQUESTS(AppConstants.EXCEPTION_TYPE.WARN, CustomErrorConstants.ERR_20000,false,MessageUtil.getMessage("web.sms-code.TooManyRequests")),
    SEND_FAILED(AppConstants.EXCEPTION_TYPE.WARN,CustomErrorConstants.ERR_20001,false,MessageUtil.getMessage("web.sms-code.sendFailed")),
    INCORRECT_VERIFY_CODE(AppConstants.EXCEPTION_TYPE.WARN,CustomErrorConstants.ERR_20004,false,MessageUtil.getMessage("web.sms-code.incorrectVerifyCode")),
    SEND_FAILED_OR_EXPIRED(AppConstants.EXCEPTION_TYPE.WARN,CustomErrorConstants.ERR_20005,false,MessageUtil.getMessage("web.sms-code.sendFailedOrExpired"));

    SmsCodeExceptionEnum(String type,int code,boolean shouldLog,String message) {
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
