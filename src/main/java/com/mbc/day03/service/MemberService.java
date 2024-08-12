package com.mbc.day03.service;

import com.mbc.day03.domain.MemberDao;
import com.mbc.day03.domain.MemberVo;
import com.mbc.day03.domain.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<MemberVo> getList() {
        List<MemberVo> list = memberDao.getList();
        return list;
    }

    public List<MemberVo> memberSearch(String sDate, String eDate, String name, String gender) {
        List<MemberVo> list = memberDao.memberSearch(sDate, eDate, name, gender);
        return list;
    }

    public int memberRegister(MemberVo mvo) {
        // 암호화전
//        int n = memberDao.memberInsert(mvo);

        // 암호화 후 입력
        mvo.setPw(passwordEncoder.encode(mvo.getPw()));
        int n = memberDao.memberInsert(mvo);
        return n;
    }

    public MemberVo getMember(String mid) {
        MemberVo mvo = memberDao.getMember(mid);
        return mvo;
    }

    @Transactional
    public int memberModify(MemberVo mVo) {
        int n = memberDao.memberModify(mVo);
        orderDao.updateMember(mVo);
        return n;
    }
}
