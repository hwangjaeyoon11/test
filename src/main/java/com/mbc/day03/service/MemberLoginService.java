package com.mbc.day03.service;

import com.mbc.day03.domain.MemberLoginDao;
import com.mbc.day03.domain.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class MemberLoginService {

    @Autowired
    MemberLoginDao memberLoginDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 로그인 하기
    public boolean userLogin(MemberVo mvo,
                             HttpServletRequest request){

        HttpSession session =request.getSession();

        // 아이디와 일치하는 회원정보 가져오기
        MemberVo memberVo = memberLoginDao.userLogin(mvo);

        if(memberVo != null){
            String inputPw= mvo.getPw(); // 사용자 입력 비번
            String dbpw = memberVo.getPw();

            // 비번 일치
            if(true == passwordEncoder.matches(inputPw, dbpw)){
                session.setAttribute("memberLoginVo",memberVo);
                System.out.println("로그인 성공!!");
                return true;
            }else{ // 비번 불일치
                System.out.println("로그인 실패");
            }
        }

        return false;
    }
}
