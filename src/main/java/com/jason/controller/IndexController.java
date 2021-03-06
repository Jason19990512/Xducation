package com.jason.controller;

import com.jason.mapper.CourseMapper;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Course;
import com.jason.pojo.Members;
import com.jason.utils.MemberAuthorityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    CourseMapper courseMapper;

    @RequestMapping(value = {"/","/index"})
    public String index(Model model){
        List<Course> bestCourse = courseMapper.getBestCourse();
        model.addAttribute("bestCourse",bestCourse);
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


}
