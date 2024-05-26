package com.nlsapi.core.business.service;

import com.nlsapi.core.business.enums.SmsCodeUsageEnum;

public interface SmsCodeService {


    /**
     *  发送短信验证码
      * @param account 账号
     */
  void sendForRegister(String account);

  void validCode(String account, SmsCodeUsageEnum usage, String code);

}
