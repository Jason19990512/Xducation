package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    @RequestMapping("/toUpdateMember")
    public String toUpdateMember(){
        return "front-end/members/displayMember";
    }
}
