package com.jason.controller;

import com.jason.config.SecurityConfig;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/members")
public class MemberController extends IndexController{
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toDisplayMember")
    public String toDisplayMember(Model model, HttpSession session){
        String memacc  = getUserName();

        Members member = memberMapper.getOneMemberByMemacc(memacc);

        model.addAttribute("member",member);
        return "front-end/members/displayMember";
    }

    @RequestMapping("/updateMember")
    public String updateMember(Model model){
        String memacc  = getUserName();

        Members member = memberMapper.getOneMemberByMemacc(memacc);

        model.addAttribute("member",member);
        model.addAttribute("isUpdate",true);
        return "front-end/members/addMember";
    }
}
