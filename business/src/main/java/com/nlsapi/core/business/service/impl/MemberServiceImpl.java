package com.nlsapi.core.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.nlsapi.core.business.entity.MastMemberEntity;
import com.nlsapi.core.business.entity.MastMemberEntityExample;
import com.nlsapi.core.business.enums.exception.MemberExceptionEnum;
import com.nlsapi.core.business.mapper.MastMemberEntityMapper;
import com.nlsapi.core.business.mapper.cust.CustMastMemberEntityMapper;
import com.nlsapi.core.business.req.web.MemberLoginReq;
import com.nlsapi.core.business.req.web.MemberRegisterReq;
import com.nlsapi.core.business.resp.MemberLoginResp;
import com.nlsapi.core.business.service.MemberService;
import com.nlsapi.core.common.exception.BusinessException;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MastMemberEntityMapper mastMemberEntityMapper;
  private final CustMastMemberEntityMapper custMastMemberEntityMapper;

  @Override
  public MastMemberEntity getByAccount(String account) {
      var example = new MastMemberEntityExample();
      var criteria = example.createCriteria();
      criteria.andMmAccountEqualTo(account);
      var rs = mastMemberEntityMapper.selectByExample(example);
      if (CollUtil.isNotEmpty(rs)){
          return rs.get(0);
      }
      return null;
  }

    @Override
    public void register(MemberRegisterReq req) {
        var account = req.getAccount();
        var dbMember = getByAccount(account);
        if (Objects.nonNull(dbMember)) {
            throw new BusinessException(MemberExceptionEnum.ACCOUNT_HAS_BEEN_REGISTERED);
        }
        var newMember = new MastMemberEntity();
        newMember.setMmId(IdWorkerUtil.getId());
        newMember.setMmAccount(account);
        newMember.setMmPassword(req.getPassword());

        var nickname = "";
        if (Validator.isMobile(account)){
            nickname = DesensitizedUtil.mobilePhone(account);
        } else if (Validator.isEmail(account)){
            nickname = DesensitizedUtil.email(account);
        } else {
            nickname = DesensitizedUtil.mobilePhone(account);
        }
        newMember.setMmNickname(nickname);
        custMastMemberEntityMapper.insert(newMember);
    }

    @Override
    public MemberLoginResp login(MemberLoginReq req) {
        var account = req.getAccount();
        var dbMember = getByAccount(account);
        if (Objects.isNull(dbMember)) {
            // 账号不存在
            throw new BusinessException(MemberExceptionEnum.LOGIN_FAILED);
        }
        if (StrUtil.equals(dbMember.getMmPassword(), req.getPassword())) {
            var newMemberResp = new MemberLoginResp();
            newMemberResp.setNickname(dbMember.getMmNickname());
            newMemberResp.setId(dbMember.getMmId());
            var map = BeanUtil.beanToMap( newMemberResp);
            String token = JwtUtil.createLoginToken(map);
            newMemberResp.setToken(token);
            return newMemberResp;
        } else {
            // 密码错误
            throw new BusinessException(MemberExceptionEnum.LOGIN_FAILED);
        }
    }

}
