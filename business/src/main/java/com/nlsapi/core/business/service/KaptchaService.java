package com.nlsapi.core.business.service;

public interface KaptchaService {

    void validCode(String code, String token);

}
