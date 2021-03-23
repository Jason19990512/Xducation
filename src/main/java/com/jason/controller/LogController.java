package com.jason.controller;

import com.jason.mapper.MemberMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/log")
public class LogController extends IndexController{

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toLogin")
    public String tologin(){
        return "front-end/login/login";
    }



//    @RequestMapping("/login")
//    public ModelAndView login(@RequestParam("memacc")String memacc, @RequestParam("mempwd")String mempwd, HttpSession session){
//
//        ModelAndView modelAndView = null ;
//        Members member = memberMapper.getOneMemberByMemacc(memacc);
//        logger.info(member.toString());
//        if(member != null && mempwd.equals(member.getMempwd())){
//            modelAndView = new ModelAndView("front-end/index/index");
//        }else{
//            member = new Members();
//            member.setMemacc(memacc);
//            member.setMempwd(mempwd);
//            modelAndView = new ModelAndView("front-end/login/login");
//            modelAndView.addObject("member",member);
//            modelAndView.addObject("msg","帳號密碼輸入錯誤");
//        }
//        return modelAndView;
//    }
}
