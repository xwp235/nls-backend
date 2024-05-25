package com.nlsapi.core.common.exception;

import com.nlsapi.core.common.exception.enums.IBusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class BusinessException extends RuntimeException{

    private IBusinessException errorInfo;

    public BusinessException(IBusinessException e) {
        super(e.getMessage());
        this.errorInfo = e;
    }

    /**
     * 不写入堆栈信息，简化异常日志，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
