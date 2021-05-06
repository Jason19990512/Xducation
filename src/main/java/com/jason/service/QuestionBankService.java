package com.jason.service;

import com.jason.pojo.QuestionBank;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionBankService {
    boolean checkQuestionSum(String refcourseno, String testscope);
    List<QuestionBank> doRandomQuestionOrder(String refcourseno, String level, String testscope, String memacc, int testno);
    void  correctTestPapper(HttpServletRequest request, String memacc);
    List<QuestionBank> getTestResult(HttpServletRequest request);
}
