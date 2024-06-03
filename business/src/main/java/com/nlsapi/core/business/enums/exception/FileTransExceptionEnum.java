package com.nlsapi.core.business.enums.exception;

import com.nlsapi.core.business.CustomErrorConstants;
import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.exception.enums.IBusinessException;
import com.nlsapi.core.common.utils.MessageUtil;

public enum FileTransExceptionEnum implements IBusinessException {

    CALC_AMOUNT_FAILED(AppConstants.EXCEPTION_TYPE.WARN, CustomErrorConstants.ERR_20008,false,MessageUtil.getMessage("web.file-trans.calcAmountFailed")),;

    FileTransExceptionEnum(String type, int code, boolean shouldLog, String message) {
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
