package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.dao.TravelItemMapper;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
}
