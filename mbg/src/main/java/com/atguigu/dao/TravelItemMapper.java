package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.atguigu.pojo.TravelItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravelItemMapper {
    long countByExample(TravelItemExample example);

    int deleteByExample(TravelItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TravelItem record);

    int insertSelective(TravelItem record);

    List<TravelItem> selectByExample(TravelItemExample example);

    TravelItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TravelItem record, @Param("example") TravelItemExample example);

    int updateByExample(@Param("record") TravelItem record, @Param("example") TravelItemExample example);

    int updateByPrimaryKeySelective(TravelItem record);

    int updateByPrimaryKey(TravelItem record);
}