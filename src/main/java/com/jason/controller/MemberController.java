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
        String memacc = "";
        if(getUserName().indexOf("Members") != -1){
            Map currentMap = (HashMap) session.getAttribute("currentMap");
            String key = (String)(currentMap.keySet().toArray()[0]);
            Members member = (Members) currentMap.get(key);
            if(session.getId().equals(key)){
                memacc = member.getMemacc();
            }
        }else{
            memacc = getUserName();
        }

        Members member = memberMapper.getOneMemberByMemacc(memacc);

        model.addAttribute("member",member);
        return "front-end/members/displayMember";
    }

}
