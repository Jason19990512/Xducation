package com.jason.controller;

import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import com.jason.utils.MemberAuthorityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class IndexController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "front-end/index/index";
    }

    //從spring secutiry 中獲取當前用戶登入資料
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            return authentication.getName();
        }else{
            return "";
        }
    }

    //將使用者設定已登入
    public void setCurrentUser(String memacc) {
        Members member = memberMapper.getOneMemberByMemacc(memacc);
        //將使用者放入Authentication物件，代表已通過驗證
        Authentication auth=new UsernamePasswordAuthenticationToken(member,
                member.getMempwd(), MemberAuthorityUtils.createAuthorities(member));
        //將Authentication物件放入SecurityContext存放
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
