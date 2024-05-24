package com.nlsapi.core.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.nlsapi.core.business.entity.MastSmsCodeEntity;
import com.nlsapi.core.business.entity.MastSmsCodeEntityExample;
import com.nlsapi.core.business.enums.AccountTypeEnum;
import com.nlsapi.core.business.enums.SmsCodeStatusEnum;
import com.nlsapi.core.business.enums.SmsCodeUsageEnum;
import com.nlsapi.core.business.enums.exception.SmsCodeExceptionEnum;
import com.nlsapi.core.business.mapper.MastSmsCodeEntityMapper;
import com.nlsapi.core.business.mapper.cust.CustMastSmsCodeEntityMapper;
import com.nlsapi.core.business.service.SmsCodeService;
import com.nlsapi.core.common.exception.BusinessException;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SmsCodeServiceImpl implements SmsCodeService {

    private final CustMastSmsCodeEntityMapper custMastSmsCodeEntityMapper;
    private final MastSmsCodeEntityMapper mastSmsCodeEntityMapper;

    /**
     * 校验: 如果一分钟内有相同手机号相同用途发送记录，则报错：短信请求过于频繁
     * @param account 账号
     * @param usage 用途
     */
    @Override
    public void sendCode(String account, SmsCodeUsageEnum usage) {
       var code = RandomUtil.randomNumbers(6);

       var example = new MastSmsCodeEntityExample();
       var criteria = example.createCriteria();
       criteria.andMscAccountEqualTo(account)
               .andMscUsageEqualTo(usage.getCode())
               .andMscCreatedAtGreaterThan(DateUtil.offsetMinute(TimeUtil.getCurrentTime(), -1));
       var count = mastSmsCodeEntityMapper.countByExample(example);
       if (count > 0) {
          throw new BusinessException(SmsCodeExceptionEnum.TOO_MANY_REQUESTS);
       }

       // 保存数据库
       var smsCode = new MastSmsCodeEntity();
       smsCode.setMscId(IdWorkerUtil.getId());
       smsCode.setMscAccount(account);

        var now = new Date();

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

       // 对接短信通道，发送短信

    }

}
