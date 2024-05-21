package com.nlsapi.core.common.exception;

import com.nlsapi.core.common.AppConstants;
import com.nlsapi.core.common.advice.IgnoreRespSerializable;
import com.nlsapi.core.common.exception.enums.BusinessExceptionEnum;
import com.nlsapi.core.common.resp.JsonResp;
import com.nlsapi.core.common.utils.LogUtil;
import com.nlsapi.core.common.utils.MessageUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(SaTokenException.class)
//    public JsonResult handleSaTokenException(SaTokenException nle) {
//        String message;
//        if (nle instanceof NotLoginException notLoginException) {
//            if (notLoginException.getType().equals(NotLoginException.NOT_TOKEN)) {
//                // code为11011
//                message = "令牌不存在或已过有效期，无法访问";
//            } else if (notLoginException.getType().equals(NotLoginException.INVALID_TOKEN)) {
//                message = "令牌无效，无法访问";
//            } else if (notLoginException.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
//                // code为11016
//                message = "令牌已过期,请重新登录";
//            } else if (notLoginException.getType().equals(NotLoginException.BE_REPLACED)) {
//                message = "您已在其他客户端上线";
//            } else if (notLoginException.getType().equals(NotLoginException.KICK_OUT)) {
//                message = "您已被管理员踢下线";
//            } else {
//                message = "未登录无法访问";
//            }
//            return JsonResult.error(message)
//                    .setCode("E"+nle.getCode());
//        }
//        return JsonResult.error(nle.getMessage())
//                .setCode("E"+nle.getCode());
//    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    public JsonResp handleBindException(BindException e) {
        var err = e.getFieldError();
        var resp = JsonResp.error().set(AppConstants.ERROR_TYPE,AppConstants.EXCEPTION_TYPE.WARN)
                .set(AppConstants.TRACE_ID,MDC.get(AppConstants.LOG_ID)).setCode(BusinessExceptionEnum.INVALID_REQUEST_PARAMETER.getCode());
        if (Objects.nonNull(err)) {
            if (Objects.nonNull(err.getDefaultMessage())) {
                return resp.setMsg(err.getDefaultMessage());
            }
        }
        var message = MessageUtil.getMessage("invalidRequestParameters");
        return resp.setMsg(message);
    }

    @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    public JsonResp requestParamException() {
        return JsonResp.error(MessageUtil.getMessage("invalidRequestParameters")).setCode(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    public JsonResp notAcceptableException() {
        return JsonResp.error(MessageUtil.getMessage("httpMediaTypeNotAcceptable")).setCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public JsonResp httpMediaTypeNotSupportedException() {
        return JsonResp.error(MessageUtil.getMessage("requestMediaTypeNotSupported")).setCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JsonResp httpRequestMethodNotSupportedException() {
        return JsonResp.error(MessageUtil.getMessage("requestMethodNotSupported")).setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @ExceptionHandler({NoResourceFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    public JsonResp noResourceFoundException() {
        return JsonResp.error(MessageUtil.getMessage("resourceNotFound")).setCode(HttpStatus.NOT_FOUND.value());
    }

    /**
     * 业务异常统一处理
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @IgnoreRespSerializable
    public JsonResp businessException(BusinessException e) {
        var errorInfo = e.getErrorInfo();
        var resp = JsonResp.error(e.getMessage()).setCode(errorInfo.getCode())
                .set(AppConstants.ERROR_TYPE,errorInfo.getType())
                .set(AppConstants.TRACE_ID,MDC.get(AppConstants.LOG_ID));
        LogUtil.error(e.getMessage(), e);
        return resp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @IgnoreRespSerializable
    public JsonResp exception(Exception e) {
        var message = MessageUtil.getMessage("serverInternalError");
        LogUtil.error(message,e);
        return JsonResp.error(message).setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .set(AppConstants.ERROR_TYPE,AppConstants.EXCEPTION_TYPE.ERROR)
                .set(AppConstants.TRACE_ID,MDC.get(AppConstants.LOG_ID));
    }

}
