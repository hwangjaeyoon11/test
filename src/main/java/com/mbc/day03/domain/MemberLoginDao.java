package com.mbc.day03.domain;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberLoginDao {
    MemberVo userLogin(MemberVo mvo);
}
