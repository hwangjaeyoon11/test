package com.mbc.day03.controller;

import com.mbc.day03.domain.MemberVo;
import com.mbc.day03.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public String getList(Model m) {
        List<MemberVo> list = memberService.getList();
        m.addAttribute("list", list);

        return "/member/member";
    }

    @PostMapping("/member/search")
    public String memberSearch(@RequestParam("s_date") String sDate,
                               @RequestParam("e_date") String eDate,
                               @RequestParam(value = "name", defaultValue = "All") String name,
                               @RequestParam("gender") String gender,
                               Model model) {
        List<MemberVo> list = memberService.memberSearch(sDate, eDate, name, gender);
        System.out.println("sDate = " + sDate);
        System.out.println("eDate = " + eDate);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);

        model.addAttribute("list", list);
        System.out.println("list = " + list);
        return "/member/member";
    }

    @GetMapping("/member/memRegister")
    public String memberRegister(){
        return "/member/memberRegister";
    }

    @PostMapping("/member/memRegister")
    public String memberRegisterPost(MemberVo mvo){
        int n = memberService.memberRegister(mvo);
        return "redirect:/member";
    }

    @GetMapping("/member/memModify")
    public String memberModify(@RequestParam("mid") String mid,
                               Model m) {
        MemberVo mvo = memberService.getMember(mid);
        m.addAttribute("mvo", mvo);

        return "member/memberModify";
    }

    @PostMapping("/member/memModify")
    public String memberModifyPost(MemberVo mVo) {
        int n = memberService.memberModify(mVo);

        return "redirect:/member";
    }
}
