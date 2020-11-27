package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: oono
 * @Date: 2020/11/26
 * @Description:
 */

@RestController
@RequestMapping("/travelgroup")
public class TravelGroupController {

    @Reference
    private TravelGroupService travelGroupService;

    //根据id查询跟团游信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            TravelGroup travelGroup = travelGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

    //删除跟团游
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            travelGroupService.delete(id);
            return new Result(true,MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

    //查询组团游分页信息
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelGroupService.findPage(queryPageBean);
    }

    //添加组团有
    @RequestMapping("/add")
    public Result add(Integer[] travelItemIds, @RequestBody TravelGroup travelGroup){
        try {
            travelGroupService.add(travelItemIds, travelGroup);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }
}
