package com.jason.controller;

import com.jason.config.SecurityConfig;
import com.jason.mapper.MailForLostPwdMapper;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.MailForLostPwd;
import com.jason.pojo.Members;
import com.jason.service.MailService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController extends IndexController{
    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MailForLostPwdMapper mailForLostPwdMapper;

    @Autowired
    MailService mailService;



    @RequestMapping("/toRegister")
    public String toRegister(Model model){
        model.addAttribute("registerMsg","");
        if(getUserName() != null && !StringUtils.equals("anonymousUser", getUserName())){
            model.addAttribute("member",memberMapper.getOneMemberByMemacc(getUserName()));
            model.addAttribute("isUpdate",true);
        }else{
            model.addAttribute("member",new Members());
            model.addAttribute("isUpdate",false);
        }
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
    public String addMembers(@ModelAttribute(value = "member") Members member, @RequestParam(value="file") MultipartFile file, HttpServletRequest request) {
        Integer flag = 0 ;
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                member.setMprofile("/upload/" + member.getMemacc() + "/" + fileName);
                String filePath = "C:/Users/jpg74/IdeaProjects/jason/src/main/upload/" + member.getMemacc() + "/";
                File destination = new File(filePath + fileName);
                if(!destination.exists()){
                    destination.mkdirs();
                }
                try {
                    file.transferTo(destination);
                    logger.info("Upload Success!!!");
                } catch (IOException e) {
                    logger.error(e.toString(), e);
                }
            }
            flag = memberMapper.addMember(member);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        if(flag > 0)
            return "front-end/login/login";
        else
            return "404";
    }

    @RequestMapping("/getBackPwd")
    @ResponseBody
    public String getBackPwd(@Param("memail") String memail,HttpServletRequest request) throws MessagingException {
        String exPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() ;
        return  mailService.mailGetBackPwd(memail, exPath);
    }

    @RequestMapping("/resetPwd")
    public String resetPwd(@RequestParam("sid") String sid,@RequestParam("memail") String memail, HttpSession session, Model model){

        Members member = memberMapper.getOneMemberByMememail(memail);
        model.addAttribute("memail",memail);
        boolean flag = verifyURL(sid, memail);
        if(flag)
            return "/front-end/getBackLostPwd/editNewPwd";
        else
            return "/front-end/getBackLostPwd/resetPwdError";
    }

    public boolean verifyURL(String sid, String memail){
        MailForLostPwd  lostPwdRecord = mailForLostPwdMapper.findLostPwdByMemail(memail);
        long expTime  = lostPwdRecord.getExpTime();
        String sidDB = lostPwdRecord.getSid();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        long nowTime = now.getTime();

        if(expTime < nowTime){
            logger.debug("信件超時");
            return false;
        }else if("".equals(sid)){
            logger.debug("sid不可為空");
            return false;
        }else if(!StringUtils.equals(sidDB,sid)){
            logger.debug("sid找不到");
            return false;
        }
        return true;
    }

    @RequestMapping("/saveResetPwd")
    public String saveResetPwd(@RequestParam("memail") String memail, @RequestParam("mempwd") String mempwd){
        Members member = memberMapper.getOneMemberByMememail(memail);
        member.setMempwd(mempwd);
        Integer flag = memberMapper.updateMember(member);
        if(flag > 0){
            logger.debug("重新設定密碼成功!!!");
            return "/front-end/login/login";
        }else{
            logger.debug("重新設定密碼失敗!!!");
            return "404";
        }
    }


}
