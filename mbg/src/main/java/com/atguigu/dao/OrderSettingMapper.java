package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import com.atguigu.pojo.OrderSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderSettingMapper {
    long countByExample(OrderSettingExample example);

    int deleteByExample(OrderSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSetting record);

    int insertSelective(OrderSetting record);

    List<OrderSetting> selectByExample(OrderSettingExample example);

    OrderSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSetting record, @Param("example") OrderSettingExample example);

    int updateByExample(@Param("record") OrderSetting record, @Param("example") OrderSettingExample example);

    int updateByPrimaryKeySelective(OrderSetting record);

    int updateByPrimaryKey(OrderSetting record);
}