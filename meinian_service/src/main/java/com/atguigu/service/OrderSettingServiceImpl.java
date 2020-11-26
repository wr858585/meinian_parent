package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingMapper;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.pojo.OrderSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: oono
 * @Date: 2020/11/25
 * @Description:
 */

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void batchInsert(List<OrderSetting> orderSettingList) {
        for (OrderSetting setting : orderSettingList) {
            OrderSettingExample example = new OrderSettingExample();
            OrderSettingExample.Criteria criteria = example.createCriteria();
            criteria.andOrderdateEqualTo(setting.getOrderdate());
            //找出遍历的当个日期在！数据库中！的记录数
            long count = orderSettingMapper.countByExample(example);
            //有记录，则修改数据库中该记录，变成excel中上传的数据
            if(count > 0){
                orderSettingMapper.updateByExampleSelective(setting,example);
            }else{  //没有记录，则在数据库中添加这个excel中新写的数据
                orderSettingMapper.insert(setting);
            }
        }
    }

    @Override
    public List<Map<String, Integer>> getOrderSettingByMonth(String date) {
        String dateLike = date + "-%";
        List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(dateLike);

        List<Map<String,Integer>> returnDataList = new ArrayList<>();

        for (OrderSetting setting : list) {
            int date1 = setting.getOrderdate().getDate();
            Integer number = setting.getNumber();
            Integer reservations = setting.getReservations();
            Map<String,Integer> map = new HashMap<>();
            map.put("date",date1);
            map.put("number",number);
            map.put("reservations",reservations);

            returnDataList.add(map);
        }

        return returnDataList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        OrderSettingExample example = new OrderSettingExample();
        OrderSettingExample.Criteria criteria = example.createCriteria();
        criteria.andOrderdateEqualTo(orderSetting.getOrderdate());

        long count = orderSettingMapper.countByExample(example);
        if(count > 0) {
            orderSettingMapper.updateByExampleSelective(orderSetting, example);
        }else {
            orderSettingMapper.insert(orderSetting);
        }
    }

}
