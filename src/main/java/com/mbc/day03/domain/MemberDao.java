package com.mbc.day03.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {
    List<MemberVo> getList();

    List<MemberVo> memberSearch(String sDate, String eDate, String name, String gender);

    int memberInsert(MemberVo mvo);

    MemberVo getMember(String mid);

    int memberModify(MemberVo mVo);
}
