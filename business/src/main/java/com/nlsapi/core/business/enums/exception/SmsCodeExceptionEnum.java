package com.nlsapi.core.business.enums.exception;

import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.exception.enums.IBusinessException;
import com.nlsapi.core.common.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SmsCodeExceptionEnum implements IBusinessException {

    TOO_MANY_REQUESTS(AppConstants.EXCEPTION_TYPE.WARN,20000,MessageUtil.getMessage("web.sms-code.TooManyRequests"));

    private final String type;
    private final int code;
    private final String message;

}
