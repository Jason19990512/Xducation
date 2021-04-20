package com.jason.controller;


import com.jason.pojo.QuestionBank;
import com.jason.service.Impl.QuestionBankServiceImpl;
import com.jason.service.Impl.TestServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("questionbank")
public class QuestionBankController extends IndexController{

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    QuestionBankServiceImpl questionBankServiceImpl;

    @Autowired
    TestServiceImpl testServiceImpl;

    @RequestMapping("checkquestion")
    public @ResponseBody boolean checkQuestionSum(@RequestParam("refcourseno") String refcourseno, @RequestParam("testscope") String testscope){
        return questionBankServiceImpl.checkQuestionSum(refcourseno, testscope);
    }

    @RequestMapping("printTestPapper")
    public String printTestPapper(@RequestParam("refcourseno") String refcourseno, @RequestParam("level") String level, @RequestParam("testscope") String testscope, Model model) {
        String memacc = getUserName();

        Integer testno = testServiceImpl.makeTestNo(refcourseno, memacc, testscope);
        logger.info("testno >>> " + testno);
        List<QuestionBank> seletedQuestion = questionBankServiceImpl.doRandomQuestionOrder(refcourseno, level, testscope, memacc, testno);

        model.addAttribute("testno", testno);
        model.addAttribute("seletedQuestion", seletedQuestion);
        return "front-end/test/printQuestion";
    }

    @RequestMapping("correctTestPapper")
    public String correctTestPapper(HttpServletRequest request) throws ServletException, IOException {
        String testno = request.getParameter("testno");
        questionBankServiceImpl.correctTestPapper(request, testno);
        return "front-end/test/printQuestion";
    }

}
