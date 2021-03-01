package com.jason;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/hello")
    public String test(){
        return "hello";
    }
}
