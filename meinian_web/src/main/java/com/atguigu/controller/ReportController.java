package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: oono
 * @Date: 2020/11/29
 * @Description:
 */

@RestController
@RequestMapping("/report")
public class ReportController {

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,null);
    }

}
