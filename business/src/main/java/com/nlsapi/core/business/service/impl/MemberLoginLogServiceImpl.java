package com.nlsapi.core.business.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.nlsapi.core.business.context.LoginMemberContext;
import com.nlsapi.core.business.entity.MemberLoginLogEntity;
import com.nlsapi.core.business.entity.MemberLoginLogEntityExample;
import com.nlsapi.core.business.mapper.MemberLoginLogEntityMapper;
import com.nlsapi.core.business.mapper.cust.CustMemberLoginLogEntityMapper;
import com.nlsapi.core.business.resp.MemberLoginResp;
import com.nlsapi.core.business.service.IMemberLoginLogService;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.LogUtil;
import com.nlsapi.core.common.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberLoginLogServiceImpl implements IMemberLoginLogService {

  private final CustMemberLoginLogEntityMapper custMemberLoginLogEntityMapper;
  private final MemberLoginLogEntityMapper memberLoginLogEntityMapper;

  @Override
  public void save(MemberLoginResp resp) {
      LogUtil.info("增加会员登录日志：{}",resp);
      var memberLoginLogEntity = new MemberLoginLogEntity();
      memberLoginLogEntity.setId(IdWorkerUtil.getId());
      memberLoginLogEntity.setMemberId(resp.getId());
      memberLoginLogEntity.setToken(resp.getToken());
      memberLoginLogEntity.setHeartCount(0);
      custMemberLoginLogEntityMapper.insertSelective(memberLoginLogEntity);
  }

  @Override
    public void updateHeartInfo() {
        MemberLoginResp memberLoginResp = LoginMemberContext.getMember();
        String token = memberLoginResp.getToken();
        LogUtil.info("更新会员心跳信息：{}", token);
        MemberLoginLogEntityExample memberLoginLogExample = new MemberLoginLogEntityExample();
        memberLoginLogExample.createCriteria().andTokenEqualTo(token);
        memberLoginLogExample.setOrderByClause("id desc");
        List<MemberLoginLogEntity> memberLoginLogList = memberLoginLogEntityMapper.selectByExample(memberLoginLogExample);
        if (CollUtil.isEmpty(memberLoginLogList)) {
            LogUtil.warn("未找到该token的登录信息：{}，会员ID：{}", token, memberLoginResp.getId());
            save(memberLoginResp);
            return;
        }
        var memberLoginLogDB = memberLoginLogList.get(0);
        memberLoginLogDB.setHeartCount(memberLoginLogDB.getHeartCount() + 1);
        memberLoginLogDB.setLastHeartTime(TimeUtil.getCurrentTime());
        memberLoginLogEntityMapper.updateByPrimaryKey(memberLoginLogDB);
    }
}
