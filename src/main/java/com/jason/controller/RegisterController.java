package com.jason.controller;

import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {
    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toRegister")
    public String toRegister(Model model){
        model.addAttribute("registerMsg","");
        model.addAttribute("member",new Members());
        return "front-end/members/addMember";
    }

    @RequestMapping("/registerCheckMemacc")
    @ResponseBody
    public String registerCheckMemacc(@RequestParam("memacc") String memacc) {
        Integer flag = memberMapper.registerCheckMemacc(memacc);
        if( flag > 0 )
            return "false";
        else
            return "true";
    }

    @RequestMapping("/registerCheckMemail")
    @ResponseBody
    public String registerCheckMemail(@RequestParam("memail") String memail) {
        Integer flag = memberMapper.registerCheckMemail(memail);
        if( flag > 0 )
            return "false";
        else
            return "true";
    }

    @RequestMapping("/addMembers")
    public String addMembers(@ModelAttribute(value = "member") Members member, @RequestParam("file") MultipartFile file, HttpServletRequest request,Model model) {

        try {
            if(memberMapper.registerCheckMemacc(member.getMemacc()) > 0 || memberMapper.registerCheckMemail(member.getMemail()) > 0 ){
                model.addAttribute("registerMsg","帳號註冊失敗，確認帳號與信箱是否被使用");
                return "front-end/members/addMember";
            }
            Integer flag = memberMapper.addMember(member);

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String filePath = "C:/Users/jpg74/IdeaProjects/jason/src/main/resources/upload/";
                File destination = new File(filePath + fileName);
                try {
                    file.transferTo(destination);
                    logger.info("會員圖片上傳成功成功");
                } catch (IOException e) {
                    logger.error(e.toString(), e);
                }
            }

        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return "front-end/login/login";
    }
}
