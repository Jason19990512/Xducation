package com.jason.controller;

import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpSession;



@Controller
public class LoginController {

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toLogin")
    public String tologin(){
        return "font-end/login/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("memacc")String memacc, @RequestParam("mempwd")String mempwd, HttpSession session){
        ModelAndView modelAndView = null ;
        Members member = memberMapper.checkMemacc(memacc);
        if(member != null && mempwd.equals(member.getMempwd())){
            modelAndView = new ModelAndView("font-end/index/index");
            modelAndView.addObject("isLogin","true");
        }else{
            member = new Members();
            member.setMemacc(memacc);
            member.setMempwd(mempwd);
            modelAndView = new ModelAndView("font-end/login/login");
            modelAndView.addObject("member",member);
            modelAndView.addObject("msg","帳號密碼輸入錯誤");
        }
        return modelAndView;
    }
}
