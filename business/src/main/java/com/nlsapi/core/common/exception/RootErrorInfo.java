package com.nlsapi.core.common.exception;

import com.nlsapi.core.common.utils.MessageUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RootErrorInfo {

    private Integer lineNumber;
    private String className;
    private String methodName;

    @Override
    public String toString() {
        return MessageUtil.getMessage("rootErrorPosition", className,methodName,lineNumber+"");
    }
}
