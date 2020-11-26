package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import com.atguigu.pojo.OrderSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    void batchInsert(@Param("orderSettingList") List<OrderSetting> orderSettingList);

    List<OrderSetting> getOrderSettingByMonth(String dateLike);
}