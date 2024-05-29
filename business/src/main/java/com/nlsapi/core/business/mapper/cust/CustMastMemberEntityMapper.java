package com.nlsapi.core.business.mapper.cust;

import com.nlsapi.core.business.entity.MastMemberEntity;

public interface CustMastMemberEntityMapper {

    int insert(MastMemberEntity row);
    int updateByPrimaryKeySelective(MastMemberEntity row);
}
