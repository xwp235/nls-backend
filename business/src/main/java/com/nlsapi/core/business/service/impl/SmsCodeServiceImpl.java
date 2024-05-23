package com.nlsapi.core.business.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.nlsapi.core.business.entity.MastSmsCodeEntity;
import com.nlsapi.core.business.enums.AccountTypeEnum;
import com.nlsapi.core.business.enums.SmsCodeStatusEnum;
import com.nlsapi.core.business.enums.SmsCodeUsageEnum;
import com.nlsapi.core.business.mapper.cust.CustMastSmsCodeEntityMapper;
import com.nlsapi.core.business.service.SmsCodeService;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsCodeServiceImpl implements SmsCodeService {

    private final CustMastSmsCodeEntityMapper custMastSmsCodeEntityMapper;

    @Override
    public void sendCode(String account, SmsCodeUsageEnum usage) {
       var code = RandomUtil.randomNumbers(6);
       // 保存数据库
       var smsCode = new MastSmsCodeEntity();
       smsCode.setMscId(IdWorkerUtil.getId());
       smsCode.setMscAccount(account);
       if (Validator.isEmail(account)) {
           smsCode.setMscAccountType(AccountTypeEnum.EMAIL.getCode());
       } else if (Validator.isMobile(account)){
           smsCode.setMscAccountType(AccountTypeEnum.MOBILE.getCode());
       } else {
           smsCode.setMscAccountType(AccountTypeEnum.MOBILE.getCode());
       }
       smsCode.setMscCode(code);
       smsCode.setMscUsage(usage.getCode());
       smsCode.setMscStatus(SmsCodeStatusEnum.NOT_USED.getCode());
       custMastSmsCodeEntityMapper.insert(smsCode);
    }

}
