package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

/**
 * @Author: oono
 * @Date: 2020/11/26
 * @Description:
 */
public interface TravelGroupService {
    void add(Integer[] travelItemsIds, TravelGroup travelGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    TravelGroup findById(Integer id);
}
