package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author oono
 * @date 2020 11 23
 */

@Controller
@RequestMapping(value = "/travelItem")
public class TravelItemController {

    @Reference  //service层注入为远程注入，所以要@Reference，而非@Autowired
    private TravelItemService travelItemService;

    @ResponseBody
    @RequestMapping("/findAll")
    public Result findAll(){
        return null;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody TravelItem travelItem){
        try{
            travelItemService.edit(travelItem);
            return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }

    //编辑自由行1a：点击编辑窗口，弹出窗口中需要回显这个travelItem中的各项数据给用户，之后再重新编辑！
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(Integer id){
        try{
            TravelItem travelItem = travelItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    //删除自由行
    @RequestMapping(value="/delete")
    @ResponseBody
    public Result delete(Integer id){
        try{
            travelItemService.deleteItemById(id);
            return  new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        }catch (Exception ex){
            ex.printStackTrace();
            return  new Result(false,ex.getMessage());
        }
    }

    //查找分页
    @PostMapping("/findPage")   //@GetMapping,@PostMapping为@RequestMapping的请求方式为Get,Post
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return travelItemService.findPage(queryPageBean);
    }

    //新增自由行
    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody TravelItem travelItem){
        try {
            travelItemService.add(travelItem);
            return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
    }



}
