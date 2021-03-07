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
@RequestMapping("/log")
public class LogController {

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toLogin")
    public String tologin(){
        return "front-end/login/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("memacc")String memacc, @RequestParam("mempwd")String mempwd, HttpSession session){
        ModelAndView modelAndView = null ;
        Members member = memberMapper.loginCheckMemacc(memacc);
        if(member != null && mempwd.equals(member.getMempwd())){
            modelAndView = new ModelAndView("front-end/index/index");
            session.setAttribute("isLogin",true);
            session.setAttribute("memacc",memacc);
        }else{
            member = new Members();
            member.setMemacc(memacc);
            member.setMempwd(mempwd);
            modelAndView = new ModelAndView("front-end/login/login");
            modelAndView.addObject("member",member);
            modelAndView.addObject("msg","帳號密碼輸入錯誤");
        }
        return modelAndView;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
            session.invalidate();
            return  "front-end/index/index";
    }
}
