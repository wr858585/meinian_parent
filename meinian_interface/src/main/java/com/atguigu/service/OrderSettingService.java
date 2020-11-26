package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author: oono
 * @Date: 2020/11/25
 * @Description:
 */
public interface OrderSettingService {

    void batchInsert(List<OrderSetting> orderSettingList);

    List<Map<String, Integer>> getOrderSettingByMonth(String data);

    void editNumberByDate(OrderSetting orderSetting);
}
