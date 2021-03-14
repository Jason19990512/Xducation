package com.jason.service;


import com.jason.mapper.MailForLostPwdMapper;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.MailForLostPwd;
import com.jason.utils.Md5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;

@Service
public class MailService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MailForLostPwdMapper mailForLostPwdMapper;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String mailGetBackPwd(String memail,String exPath){
        String result = "";
        String key = ((int) (Math.random() * 1000000) )+ "";
        // 30分後過期
        Timestamp exp = new Timestamp(System.currentTimeMillis() + (long) (30 * 60 * 1000));
        long expTime = exp.getTime();
        String sid  = memail + "&" + key + "&" + expTime;

        MailForLostPwd lostPwd = new MailForLostPwd(memail,Md5Util.md5(sid),expTime);

        try {
            MailForLostPwd lostPwdRecord = mailForLostPwdMapper.findLostPwdByMemail(memail);
            if (lostPwdRecord != null) {
                mailForLostPwdMapper.deleteLostPwdByMemail(memail);
                logger.debug("Delete lostPwdRecord !!!");
            }
                mailForLostPwdMapper.saveLostPwdByMemail(lostPwd);
                logger.debug("Save lostPwdRecord !!!");

            result = exPath + "/register/resetPwd?sid=" + Md5Util.md5(sid) + "&memail=" + memail;
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("找回密碼信件");
            helper.setText("<b style='color:red'>請到此連結，重設密碼</b><br/>" + result + "<br/>該郵件有效時間只有30分鐘，請盡快處理", true);
            helper.setTo(memail);
            helper.setFrom(from);
            mailSender.send(mimeMessage);
            logger.debug("success to send mail");
        }catch (Exception e){
            logger.debug("fail to send mail" + e) ;
            return  "false";
        }
        return "true";
    }
}
