package com.jason.controller;

import com.jason.mapper.MailForLostPwdMapper;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.MailForLostPwd;
import com.jason.pojo.Members;
import com.jason.service.MailService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/register")
public class RegisterController {
    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MailForLostPwdMapper mailForLostPwdMapper;

    @Autowired
    MailService mailService;

    /**
     * 導到註冊頁面
     * @param model
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(Model model){
        model.addAttribute("registerMsg","");
        model.addAttribute("member",new Members());
        return "front-end/members/addMember";
    }

    /**
     * 檢查帳號是否已經被註冊過
     * @param memacc
     * @return
     */
    @RequestMapping("/registerCheckMemacc")
    @ResponseBody
    public String registerCheckMemacc(@RequestParam("memacc") String memacc) {
        Integer flag = memberMapper.registerCheckMemacc(memacc);
        if( flag > 0 )
            return "false";
        else
            return "true";
    }

    /**
     * 檢查信箱是否已經被註冊過
     * @param memail
     * @return
     */
    @RequestMapping("/registerCheckMemail")
    @ResponseBody
    public String registerCheckMemail(@RequestParam("memail") String memail) {
        Integer flag = memberMapper.registerCheckMemail(memail);
        if( flag > 0 )
            return "false";
        else
            return "true";
    }

    /**
     * 加入會員
     * @param member
     * @param file
     * @param request
     * @param model
     * @return
     */
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

    /**
     * 寄送重新註冊密碼信件
     * @param memail
     * @param request
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/getBackPwd")
    @ResponseBody
    public String getBackPwd(@Param("memail") String memail,HttpServletRequest request) throws MessagingException {
        String exPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() ;
        return  mailService.mailGetBackPwd(memail, exPath);
    }

    /**
     * 確認網址送點進來的參數是否失效，並導到重新設定密碼頁面或是錯誤失效頁面
     * @param sid
     * @param memail
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(@RequestParam("sid") String sid,@RequestParam("memail") String memail, HttpSession session, Model model){
        logger.info("sid >>>>>> " + sid);
        logger.info("memail >>>>>> " + memail);

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

    /**
     * 參數 -- > 重新設定會員密碼
     * @param memail
     * @param mempwd
     */
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
