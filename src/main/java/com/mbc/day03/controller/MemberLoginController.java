package com.mbc.day03.controller;

import com.mbc.day03.domain.MemberVo;
import com.mbc.day03.service.MemberLoginService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberLoginController {
    @Autowired
    private MemberLoginService memberLoginService;

    // 로그인 페이지 이동
    @GetMapping("/userLogin")
    public String userLogin(){
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/userLogin")
    public String userLogin(MemberVo mvo,
                            HttpServletRequest req,
                            RedirectAttributes rttr){

        boolean result = memberLoginService.userLogin(mvo, req);

        if(!result){ // 로그인 실패
            rttr.addAttribute("result", 0);
            return "redirect:member/login";
        }
        HttpSession session = req.getSession();
        session.setAttribute("mvo", mvo);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/userLogout")
    public String userLogout(HttpSession session){
        session.invalidate(); // 세션 초기화
        return "redirect:/";
    }
}
