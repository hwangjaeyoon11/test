package com.mbc.day03.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.List;

@Mapper
public interface ProductDao {
           // 컬럼,   데이터
    List<Map<String, Object>> getList();

    int prodInsert(String pname,
                   String kind,
                   String price);

    int prodDel(String pno);

    Map<String, Object> getListOne(String pno);

    int prodModify(String pno, String pname, String kind, String price);

    int prodsDelete(List<String> chkList);

    int modifyPriceOne(List<String> chkList, String price);

    List<Map<String, Object>> prodSearch(String pname, String kind);

    int insertLog(String pno, String price);
    int insertLogOne(List<String> chkList, String price);

    int modifyPrice(String pno, String price);
}
