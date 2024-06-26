package com.nlsapi.core.business.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.nlsapi.core.business.entity.MastSmsCodeEntity;
import com.nlsapi.core.business.entity.MastSmsCodeEntityExample;
import com.nlsapi.core.business.enums.AccountTypeEnum;
import com.nlsapi.core.business.enums.SmsCodeStatusEnum;
import com.nlsapi.core.business.enums.SmsCodeUsageEnum;
import com.nlsapi.core.business.enums.exception.MemberExceptionEnum;
import com.nlsapi.core.business.enums.exception.SmsCodeExceptionEnum;
import com.nlsapi.core.business.mapper.MastSmsCodeEntityMapper;
import com.nlsapi.core.business.mapper.cust.CustMastSmsCodeEntityMapper;
import com.nlsapi.core.business.service.MemberService;
import com.nlsapi.core.business.service.SmsCodeService;
import com.nlsapi.core.common.exception.BusinessException;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.LogUtil;
import com.nlsapi.core.common.utils.SmsUtil;
import com.nlsapi.core.common.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SmsCodeServiceImpl implements SmsCodeService {

    private final CustMastSmsCodeEntityMapper custMastSmsCodeEntityMapper;
    private final MastSmsCodeEntityMapper mastSmsCodeEntityMapper;

    private final MemberService memberService;

    @Override
    public void sendForRegister(String account) {
        var member = memberService.getByAccount(account);
        if (Objects.nonNull(member)) {
            throw new BusinessException(MemberExceptionEnum.ACCOUNT_HAS_BEEN_REGISTERED);
        }
        sendSmsCode(account,SmsCodeUsageEnum.REGISTER);
    }

    @Override
    public  void sendForReset(String account) {
        var member = memberService.getByAccount(account);
        if (Objects.isNull(member)) {
            throw new BusinessException(MemberExceptionEnum.ACCOUNT_NOT_REGISTERED);
        }
        sendSmsCode(account,SmsCodeUsageEnum.RESET);
    }

    /**
     * 校验: 如果一分钟内有相同账号，相同用途发送记录，则报错：短信请求过于频繁
     * @param account 账号
     * @param usage 用途
     */
    private void sendSmsCode(String account, SmsCodeUsageEnum usage) {
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
       if (Validator.isMobile(account)){
           SmsUtil.sendCode(account, code);
       } else {

       }

    }

    /**
     * 通用校验验证码
     * 5分钟内，同账号，同用途，未使用过的验证码才算有效
     * 只校验最后一次验证码
     */
    public void validCode(String account, SmsCodeUsageEnum usage, String code) {
        var example = new MastSmsCodeEntityExample();
        var criteria = example.createCriteria();
        criteria.andMscAccountEqualTo(account)
                .andMscUsageEqualTo(usage.getCode())
                .andMscCreatedAtGreaterThan(DateUtil.offsetMinute(TimeUtil.getCurrentTime(), -5))
                .andMscStatusEqualTo(SmsCodeStatusEnum.NOT_USED.getCode());
        example.setOrderByClause("msc_created_at desc");
        var smsCodeList = mastSmsCodeEntityMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(smsCodeList)){
          var dbCode = smsCodeList.get(0);
          if (StrUtil.equalsIgnoreCase(dbCode.getMscCode(),code)){
             dbCode.setMscStatus(SmsCodeStatusEnum.USED.getCode());
             custMastSmsCodeEntityMapper.updateByPrimaryKeySelective(dbCode);
          } else {
              LogUtil.warn("错误的验证码,疑似黑客攻击,账号:{},输入验证码:{},用途:{}",account,usage,code);
              throw new BusinessException(SmsCodeExceptionEnum.INCORRECT_VERIFY_CODE);
          }
        } else {
            LogUtil.warn("验证码未发送或已过期,疑似黑客攻击,账号:{},输入验证码:{},用途:{}",account,usage,code);
            throw new BusinessException(SmsCodeExceptionEnum.SEND_FAILED_OR_EXPIRED);
        }

    }


}
