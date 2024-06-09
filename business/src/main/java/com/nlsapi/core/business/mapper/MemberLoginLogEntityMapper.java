package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.MemberLoginLogEntity;
import com.nlsapi.core.business.entity.MemberLoginLogEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberLoginLogEntityMapper {
    long countByExample(MemberLoginLogEntityExample example);

    int deleteByExample(MemberLoginLogEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberLoginLogEntity row);

    int insertSelective(MemberLoginLogEntity row);

    List<MemberLoginLogEntity> selectByExample(MemberLoginLogEntityExample example);

    MemberLoginLogEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MemberLoginLogEntity row, @Param("example") MemberLoginLogEntityExample example);

    int updateByExample(@Param("row") MemberLoginLogEntity row, @Param("example") MemberLoginLogEntityExample example);

    int updateByPrimaryKeySelective(MemberLoginLogEntity row);

    int updateByPrimaryKey(MemberLoginLogEntity row);
}