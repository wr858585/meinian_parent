package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: oono
 * @Date: 2020/11/25
 * @Description:
 */

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping(value = "/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String data){
        try {
            List<Map<String,Integer>> list = orderSettingService.getOrderSettingByMonth(data);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    //上传excel
    @RequestMapping("/upload")
     public Result upload(MultipartFile excelFile){
        try{
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettingList = new ArrayList<>();
            //遍历上传的文件
            for (String[] strings : list) {
                //取出对应的属性，封装到OrderSetting
                OrderSetting orderSetting = new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
                //遍历添加
                orderSettingList.add(orderSetting);
            }
            //调用service业务方法，进行保存
            orderSettingService.batchInsert(orderSettingList);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }


    }

}
