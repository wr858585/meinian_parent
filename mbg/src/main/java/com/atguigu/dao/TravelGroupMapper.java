package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravelGroupMapper {
    long countByExample(TravelGroupExample example);

    int deleteByExample(TravelGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TravelGroup record);

    int insertSelective(TravelGroup record);

    List<TravelGroup> selectByExample(TravelGroupExample example);

    TravelGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TravelGroup record, @Param("example") TravelGroupExample example);

    int updateByExample(@Param("record") TravelGroup record, @Param("example") TravelGroupExample example);

    int updateByPrimaryKeySelective(TravelGroup record);

    int updateByPrimaryKey(TravelGroup record);
}