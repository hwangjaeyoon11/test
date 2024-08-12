package com.mbc.day03.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    List<OrderDTO> getList();

    List<OrderDTO> doSearch(String sDate, String eDate, String pname, String name);

    void updateMember(MemberVo mVo);
}
