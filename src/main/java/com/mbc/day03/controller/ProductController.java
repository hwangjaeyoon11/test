package com.mbc.day03.controller;

import com.mbc.day03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String product(Model m){
        //ctrl + alt + v
        List<Map<String, Object>> list = productService.getList();
        // soutv : 위의 변수값을 콘솔에 출력
        System.out.println("list = " + list);

        m.addAttribute("list", list);
        return "/product/product";
    }

    @GetMapping("/product/prodRegister")
    public String prodRegister() {
        return "/product/prodRegister";
    }

    @PostMapping("/product/prodRegister")
    public String prodRegisterPost(@RequestParam("pname") String pname,
                                   @RequestParam("kind") String kind,
                                   @RequestParam("price") String price) {

        int n = productService.prodRegister(pname, kind, price);

        return "redirect:/product";
    }

    @GetMapping("/product/prodDel")
    public String prodDel(@RequestParam("pno") String pno) {
        int n = productService.prodDel(pno);

        return "redirect:/product";
    }

    @GetMapping("/product/prodModify")
    public String prodModify(@RequestParam("pno") String pno, Model m) {
        Map<String, Object> map = productService.getListOne(pno);

        m.addAttribute("map", map);

        return "/product/prodModify";
    }

    @PostMapping("/product/prodModify")
    public String prodModifyPost(@RequestParam("pno") String pno,
                                 @RequestParam("pname") String pname,
                                 @RequestParam("kind") String kind,
                                 @RequestParam("price") String price) {
        int n = productService.prodModify(pno, pname, kind, price);

        return "redirect:/product";
    }

    // 선택 삭제
    @PostMapping("/product/prodsDelete")
    public String prodsDelete(@RequestParam("chkPno") List<String> chkList) {
        System.out.println("#################");
        System.out.println("chkList = " + chkList);

        if (chkList != null) {
            int n = productService.prodsDelete(chkList);
        }

        return "redirect:/product";
    }

    // 선택 수정
    @PostMapping("/product/prodsModify")
    public String prodsModify(@RequestParam("chkPno") List<String> chkList,
                              @RequestParam("in_price") String price) {
//        if (chkList != null) {
//            int n = productService.modifyPriceOne(chkList, price);
//        }

        // 상품 가격 수정 & 로그 기록
        // 수정 상품이 10개이면 DB 접속 20번 접속
//        for(String pno :chkList){
//            // 가격 수정
//            int n = productService.modifyPrice(pno, price);
//            // 로그 기록
//            int n2 = productService.insertLog(pno, price);
//        }

        // 수정 상품이 10개이면 DB 접속 수 2
        if (chkList != null) {
            int n = productService.modifyPriceOne(chkList, price);
            int n2 = productService.insertLogOne(chkList, price);
        }

        return "redirect:/product";
    }

    // 상품 검색
    @PostMapping("/product/search")
    public String prodSearch(@RequestParam(value="pname", defaultValue = "all") String pname,
                             @RequestParam("kind") String kind,
                             Model m) {
        List<Map<String, Object>> list = productService.prodSearch(pname, kind);

        m.addAttribute("list", list);
        return "/product/product";
    }


}
