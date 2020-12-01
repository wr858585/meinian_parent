package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.SetmealMapper;
import com.atguigu.dao.SetmealTravelgroupMapper;
import com.atguigu.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: oono
 * @Date: 2020/11/29
 * @Description:
 */

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealTravelgroupMapper setmealTravelgroupMapper;

    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal){
        //需要在两张表中都保存添加的数据
        //1. 保存套餐游的表单数据封装号的setmeal到t_setmeal
        //特殊需求：希望要返回套餐游的id，所以要修改mapper，之后根据套餐id再绑定跟团id，这也是先保存到t_setmeal的原因
        setmealMapper.insert(setmeal);
        //2. 再把对应套餐游id的跟团游id插入到中间表t_setmeal_travelgroup
        setmealTravelgroupMapper.batchInsert(setmeal.getId(), travelgroupIds);
    }
}
