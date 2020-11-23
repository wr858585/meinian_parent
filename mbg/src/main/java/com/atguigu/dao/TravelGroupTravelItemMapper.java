package com.atguigu.dao;

import com.atguigu.pojo.TravelGroupTravelItemExample;
import com.atguigu.pojo.TravelGroupTravelItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravelGroupTravelItemMapper {
    long countByExample(TravelGroupTravelItemExample example);

    int deleteByExample(TravelGroupTravelItemExample example);

    int deleteByPrimaryKey(TravelGroupTravelItemKey key);

    int insert(TravelGroupTravelItemKey record);

    int insertSelective(TravelGroupTravelItemKey record);

    List<TravelGroupTravelItemKey> selectByExample(TravelGroupTravelItemExample example);

    int updateByExampleSelective(@Param("record") TravelGroupTravelItemKey record, @Param("example") TravelGroupTravelItemExample example);

    int updateByExample(@Param("record") TravelGroupTravelItemKey record, @Param("example") TravelGroupTravelItemExample example);
}