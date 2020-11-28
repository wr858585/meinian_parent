package com.atguigu.dao;

import com.atguigu.pojo.SetmealTravelgroupExample;
import com.atguigu.pojo.SetmealTravelgroupKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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