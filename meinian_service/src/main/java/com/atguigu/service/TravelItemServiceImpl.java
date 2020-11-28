package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupTravelItemMapper;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.dao.TravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.*;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author oono
 * @date 2020 11 23
 */

@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired  //dao层注入为本地注入，所以不是@Service，而是@Autowired
    private TravelItemMapper travelItemMapper;
    @Autowired
    private TravelGroupTravelItemMapper travelGroupTravelItemMapper;

    @Override
    public void add(TravelItem travelItem) {
        travelItemMapper.insert(travelItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {

        //分页三部曲
        //1. 导入pageHelper依赖
        //2. 在mybatis配置文件中写plugins插件信息，指定helperDialect为mysql或其他数据库，才能让pageHelper插件写出对应数据库语言
        //3. 在service层用PageHelper.startPage(pageNumber,pageSize)使用

        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        PageHelper.startPage(currentPage,pageSize);

        TravelItemExample example = new TravelItemExample();

        //防止空指针
        if(queryPageBean.getQueryString() != null && queryPageBean.getQueryString() != ""){

            //查询条件1
            TravelItemExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCodeEqualTo(queryPageBean.getQueryString());

            //查询条件2
            TravelItemExample.Criteria criteria2 = example.createCriteria();
            criteria2.andNameLike("%" + queryPageBean.getQueryString() + "%");

            example.or(criteria2);  //默认是and关系，必须修改成or才能是或关系

        }

        //按照example的逻辑查询结果
        List<TravelItem> travelItems = travelItemMapper.selectByExample(example);

        PageInfo<TravelItem> page = new PageInfo<>(travelItems);
        //PageResult实现了Serializable接口，可以实现远程传输
        return new PageResult(page.getTotal(), page.getList());
    }

    @Override
    public void deleteItemById(Integer id) {

        TravelGroupTravelItemExample example = new TravelGroupTravelItemExample();
        TravelGroupTravelItemExample.Criteria criteria = example.createCriteria();
        criteria.andTravelitemIdEqualTo(id);
        List<TravelGroupTravelItemKey> travelGroupTravelItemKeys = travelGroupTravelItemMapper.selectByExample(example);

        //有关联数据，报异常，退出不让删除
        if(travelGroupTravelItemKeys != null && travelGroupTravelItemKeys.size() > 0){
            throw new RuntimeException("有关联数据");
        }

        //允许删除
        travelItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TravelItem findById(Integer id) {
        return travelItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemMapper.updateByPrimaryKey(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        TravelItemExample example = new TravelItemExample();
        return travelItemMapper.selectByExample(example);
    }

    @Override
    public List<TravelItem> findTravelItemIdByTravelGroupId(Integer id) {
        return travelGroupTravelItemMapper.selectTravelItemIdsByTravelGroupId(id);
    }
}
