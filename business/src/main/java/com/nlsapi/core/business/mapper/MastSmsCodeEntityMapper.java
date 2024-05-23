package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.MastSmsCodeEntity;
import com.nlsapi.core.business.entity.MastSmsCodeEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MastSmsCodeEntityMapper {
    long countByExample(MastSmsCodeEntityExample example);

    int deleteByExample(MastSmsCodeEntityExample example);

    int deleteByPrimaryKey(Long mscId);

    int insert(MastSmsCodeEntity row);

    int insertSelective(MastSmsCodeEntity row);

    List<MastSmsCodeEntity> selectByExample(MastSmsCodeEntityExample example);

    MastSmsCodeEntity selectByPrimaryKey(Long mscId);

    int updateByExampleSelective(@Param("row") MastSmsCodeEntity row, @Param("example") MastSmsCodeEntityExample example);

    int updateByExample(@Param("row") MastSmsCodeEntity row, @Param("example") MastSmsCodeEntityExample example);

    int updateByPrimaryKeySelective(MastSmsCodeEntity row);

    int updateByPrimaryKey(MastSmsCodeEntity row);
}