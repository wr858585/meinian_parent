package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.dao.TravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;
import com.atguigu.pojo.TravelItemExample;
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
        List<TravelItem> travelItems = travelItemMapper.selectByExample(example);

        PageInfo<TravelItem> page = new PageInfo<>(travelItems);
        return new PageResult(page.getTotal(), page.getList());
    }
}
