package com.mbc.day03.controller;

import com.mbc.day03.domain.OrderDTO;
import com.mbc.day03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public String order(Model m) {
        List<OrderDTO> list =  orderService.getList();
        System.out.println("list = " + list);

        m.addAttribute("list", list);
        m.addAttribute("hello", "Order List");
        return "/order/order";
    }

    /* 조회하기 */
    @PostMapping("/order/search")
    public String doSearch( @RequestParam("s_date") String s_date,
                            @RequestParam("e_date") String e_date,
                            @RequestParam(value = "pname", defaultValue = "ALL") String pname,
                            @RequestParam("name") String name,
                            Model model
    ){

        List<OrderDTO> list = orderService.doSerch(s_date,e_date, pname, name);

        model.addAttribute("list", list);

        return "/order/order";
    }
}
