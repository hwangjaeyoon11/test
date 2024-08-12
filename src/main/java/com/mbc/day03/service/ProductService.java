package com.mbc.day03.service;

import com.mbc.day03.domain.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Map<String, Object>> getList(){
        List<Map<String, Object>> list = productDao.getList();
        return list;
    }

    public int prodRegister(String pname, String kind, String price) {
        int n = productDao.prodInsert(pname, kind, price);
        return n;
    }

    public int prodDel(String pno) {
        int n = productDao.prodDel(pno);
        return n;
    }

    public Map<String, Object> getListOne(String pno) {
        Map<String, Object> map = productDao.getListOne(pno);
        return map;
    }

    public int prodModify(String pno, String pname, String kind, String price) {
        int n = productDao.prodModify(pno, pname, kind, price);
        return n;
    }

    public int prodsDelete(List<String> chkList) {
        int n = productDao.prodsDelete(chkList);
        return n;
    }

    public int modifyPriceOne(List<String> chkList, String price) {
        int n = productDao.modifyPriceOne(chkList, price);
        return n;
    }

    public int modifyPrice(String pno, String price) {
        int n = productDao.modifyPrice(pno, price);
        return n;
    }

    public List<Map<String, Object>> prodSearch(String pname, String kind) {
        List<Map<String, Object>> list = productDao.prodSearch(pname, kind);
        return list;
    }

    public int insertLog(String pno, String price) {
        int n = productDao.insertLog(pno, price);
        return n;
    }

    public int insertLogOne(List<String> chkList, String price) {
        int n = productDao.insertLogOne(chkList, price);
        return n;
    }
}
