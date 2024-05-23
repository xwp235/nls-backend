package com.nlsapi.core.business.service;

import com.nlsapi.core.business.enums.SmsCodeUsageEnum;

public interface SmsCodeService {


    /**
     *  发送短信验证码
      * @param account 账号
     * @param usage 用途
     */
  void sendCode(String account, SmsCodeUsageEnum usage);

}
