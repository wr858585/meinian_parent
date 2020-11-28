package com.atguigu.dao;

import com.atguigu.pojo.SetmealTravelgroupExample;
import com.atguigu.pojo.SetmealTravelgroupKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealTravelgroupMapper {
    long countByExample(SetmealTravelgroupExample example);

    int deleteByExample(SetmealTravelgroupExample example);

    int deleteByPrimaryKey(SetmealTravelgroupKey key);

    int insert(SetmealTravelgroupKey record);

    int insertSelective(SetmealTravelgroupKey record);

    List<SetmealTravelgroupKey> selectByExample(SetmealTravelgroupExample example);

    int updateByExampleSelective(@Param("record") SetmealTravelgroupKey record, @Param("example") SetmealTravelgroupExample example);

    int updateByExample(@Param("record") SetmealTravelgroupKey record, @Param("example") SetmealTravelgroupExample example);
}