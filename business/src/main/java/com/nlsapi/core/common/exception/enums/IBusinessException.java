package com.nlsapi.core.common.exception.enums;

public interface IBusinessException {

    int getCode();
    String getType();
    boolean shouldLog();
    String getMessage();

}
