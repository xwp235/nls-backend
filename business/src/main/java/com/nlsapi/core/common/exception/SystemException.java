package com.nlsapi.core.common.exception;

import com.nlsapi.core.common.exception.enums.SystemExceptionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException{

    private SystemExceptionEnum errorInfo;

    public SystemException(SystemExceptionEnum e) {
        super(e.getMessage());
        this.errorInfo = e;
    }

}
