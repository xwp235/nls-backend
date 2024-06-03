package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.MastFileTransEntity;
import com.nlsapi.core.business.entity.MastFileTransEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MastFileTransEntityMapper {
    long countByExample(MastFileTransEntityExample example);

    int deleteByExample(MastFileTransEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MastFileTransEntity row);

    int insertSelective(MastFileTransEntity row);

    List<MastFileTransEntity> selectByExample(MastFileTransEntityExample example);

    MastFileTransEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MastFileTransEntity row, @Param("example") MastFileTransEntityExample example);

    int updateByExample(@Param("row") MastFileTransEntity row, @Param("example") MastFileTransEntityExample example);

    int updateByPrimaryKeySelective(MastFileTransEntity row);

    int updateByPrimaryKey(MastFileTransEntity row);
}