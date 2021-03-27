package com.jason.controller;

import com.jason.config.SecurityConfig;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/members")
public class MemberController extends IndexController {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    MemberMapper memberMapper;

    @RequestMapping("/toDisplayMember")
    public String toDisplayMember(Model model, HttpSession session) {
        String memacc = getUserName();

        Members member = memberMapper.getOneMemberByMemacc(memacc);

        model.addAttribute("member", member);
        return "front-end/members/displayMember";
    }

    @RequestMapping("/updateMember")
    public String updateMember(@ModelAttribute(value = "member") Members member, @RequestParam(value = "file") MultipartFile file) {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            member.setMprofile("/upload/members/" + member.getMemacc() + "/" + fileName);
            String filePath = "C:/Users/jpg74/IdeaProjects/jason/src/main/upload/members/" + member.getMemacc() + "/";
            File destination = new File(filePath + fileName);
            if (!destination.exists()) {
                destination.mkdirs();
            }
            try {
                file.transferTo(destination);
                logger.info("Upload Success!!!");
            } catch (IOException e) {
                logger.error(e.toString(), e);
            }
        }

        Integer flag = memberMapper.updateMember(member);
        if (flag > 0) {
            logger.info("更新成功");
            return "front-end/members/displayMember";
        } else{
            logger.info("更新失敗");
            return "front-end/members/addMember";
        }

    }

}
