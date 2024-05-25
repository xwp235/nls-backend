package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.MastMemberEntity;
import com.nlsapi.core.business.entity.MastMemberEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MastMemberEntityMapper {
    long countByExample(MastMemberEntityExample example);

    int deleteByExample(MastMemberEntityExample example);

    int deleteByPrimaryKey(Long mmId);

    int insert(MastMemberEntity row);

    int insertSelective(MastMemberEntity row);

    List<MastMemberEntity> selectByExample(MastMemberEntityExample example);

    MastMemberEntity selectByPrimaryKey(Long mmId);

    int updateByExampleSelective(@Param("row") MastMemberEntity row, @Param("example") MastMemberEntityExample example);

    int updateByExample(@Param("row") MastMemberEntity row, @Param("example") MastMemberEntityExample example);

    int updateByPrimaryKeySelective(MastMemberEntity row);

    int updateByPrimaryKey(MastMemberEntity row);
}