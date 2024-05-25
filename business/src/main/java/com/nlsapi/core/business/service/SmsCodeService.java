package com.nlsapi.core.business.service;

public interface SmsCodeService {


    /**
     *  发送短信验证码
      * @param account 账号
     */
  void sendForRegister(String account);

}
