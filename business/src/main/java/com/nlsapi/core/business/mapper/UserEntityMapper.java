package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.UserEntity;
import com.nlsapi.core.business.entity.UserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {
    long countByExample(UserEntityExample example);

    int deleteByExample(UserEntityExample example);

    int deleteByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int insert(UserEntity row);

    int insertSelective(UserEntity row);

    List<UserEntity> selectByExampleWithBLOBs(UserEntityExample example);

    List<UserEntity> selectByExample(UserEntityExample example);

    UserEntity selectByPrimaryKey(@Param("host") String host, @Param("user") String user);

    int updateByExampleSelective(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    int updateByExampleWithBLOBs(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    int updateByExample(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    int updateByPrimaryKeySelective(UserEntity row);

    int updateByPrimaryKeyWithBLOBs(UserEntity row);

    int updateByPrimaryKey(UserEntity row);
}