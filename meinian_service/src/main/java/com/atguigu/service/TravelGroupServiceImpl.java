package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupMapper;
import com.atguigu.dao.TravelGroupTravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelGroupExample;
import com.atguigu.pojo.TravelGroupTravelItemExample;
import com.atguigu.pojo.TravelGroupTravelItemKey;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: oono
 * @Date: 2020/11/26
 * @Description:
 */

@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupMapper travelGroupMapper;

    @Autowired
    private TravelGroupTravelItemMapper travelGroupTravelItemMapper;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        //1. 先添加跟团游信息
        travelGroupMapper.insert(travelGroup);
        Integer travelGroupId = travelGroup.getId();
        //2. 再往中间表中添加数据

/*  option1:      for (Integer travelItemsId : travelItemsIds) {
            TravelGroupTravelItemKey key = new TravelGroupTravelItemKey();
            key.setTravelgroupId(travelGroupId);
            key.setTravelitemId(travelItemsId);
            travelGroupTravelItemMapper.insert(key);
        }*/

        //option2
        travelGroupTravelItemMapper.batchInsert(travelGroupId,travelItemIds);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        //先设置分页信息
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //查询条件 三个或
        TravelGroupExample example = new TravelGroupExample();
        if(queryPageBean.getQueryString() != null && queryPageBean.getQueryString().trim() != ""){
            TravelGroupExample.Criteria criteria1 = example.createCriteria();
            TravelGroupExample.Criteria criteria2 = example.createCriteria();
            TravelGroupExample.Criteria criteria3 = example.createCriteria();

            criteria1.andCodeEqualTo(queryPageBean.getQueryString());
            criteria2.andNameLike("%" + queryPageBean.getQueryString() + "%");
            criteria3.andHelpcodeEqualTo(queryPageBean.getQueryString());

            example.or(criteria2);
            example.or(criteria3);

        }
        List<TravelGroup> travelGroups = travelGroupMapper.selectByExample(example);

        PageInfo<TravelGroup> page = new PageInfo<>(travelGroups);

        return new PageResult(page.getTotal(),page.getList());
    }

    @Override
    public void delete(Integer id) {
        travelGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        //1. 修改跟团游信息
        travelGroupMapper.updateByPrimaryKey(travelGroup);
        //2. 修改自由行ids --> 删除原有的ids，添加现在的ids
        //删除原有的
        TravelGroupTravelItemExample example = new TravelGroupTravelItemExample();
        TravelGroupTravelItemExample.Criteria criteria = example.createCriteria();
        criteria.andTravelgroupIdEqualTo(travelGroup.getId());
        travelGroupTravelItemMapper.deleteByExample(example);
        //添加新增的（之前已经写好中间表的batchInsert直接用）
        travelGroupTravelItemMapper.batchInsert(travelGroup.getId(),travelItemIds);
    }

    @Override
    public List<TravelGroup> findAll() {
        TravelGroupExample example = new TravelGroupExample();
        return travelGroupMapper.selectByExample(example);
    }
}
