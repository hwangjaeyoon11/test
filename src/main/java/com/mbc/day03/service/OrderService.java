package com.mbc.day03.service;

import com.mbc.day03.domain.OrderDTO;
import com.mbc.day03.domain.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    public List<OrderDTO> getList() {
        List<OrderDTO> list = orderDao.getList();

        return list;
    }

    public List<OrderDTO> doSerch(String sDate, String eDate, String pname, String name) {
        List<OrderDTO> list = orderDao.doSearch(sDate, eDate, pname, name);
        return list;
    }
}
