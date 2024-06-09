package com.nlsapi.core.business.mapper;

import com.nlsapi.core.business.entity.OrderInfoEntity;
import com.nlsapi.core.business.entity.OrderInfoEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoEntityMapper {
    long countByExample(OrderInfoEntityExample example);

    int deleteByExample(OrderInfoEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderInfoEntity row);

    int insertSelective(OrderInfoEntity row);

    List<OrderInfoEntity> selectByExample(OrderInfoEntityExample example);

    OrderInfoEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OrderInfoEntity row, @Param("example") OrderInfoEntityExample example);

    int updateByExample(@Param("row") OrderInfoEntity row, @Param("example") OrderInfoEntityExample example);

    int updateByPrimaryKeySelective(OrderInfoEntity row);

    int updateByPrimaryKey(OrderInfoEntity row);
}