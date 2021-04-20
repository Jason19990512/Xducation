package com.jason.service.Impl;

import com.jason.mapper.AnwserMapper;
import com.jason.mapper.QuestionBankMapper;
import com.jason.mapper.QuestionBankOptionMapper;
import com.jason.mapper.TestMapper;
import com.jason.pojo.Answer;
import com.jason.pojo.QuestionBank;
import com.jason.pojo.QuestionBankOption;
import com.jason.pojo.Test;
import com.jason.service.QuestionBankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    QuestionBankMapper questionBankMapper;

    @Autowired
    AnwserMapper anwserMapper;

    @Autowired
    QuestionBankOptionMapper questionBankOptionMapper;

    @Override
    public boolean checkQuestionSum(String refcourseno, String testscope) {
        Map map = new HashMap();
        map.put("refcourseno", refcourseno);
        map.put("testscope", testscope);
        List<QuestionBank> seletedQuestion = questionBankMapper.findQuestionByConditon(map);
        return seletedQuestion.size() > 20;
    }

    @Override
    public List<QuestionBank> doRandomQuestionOrder(String refcourseno, String level, String testscope, String memacc, int testno) {
        Map map = new HashMap();
        map.put("refcourseno", refcourseno);
        map.put("level",level);
        map.put("testscope", testscope);
        List<QuestionBank> seletedQuestion = questionBankMapper.findQuestionByConditon(map);


        List<Answer> anwserList = new ArrayList<>();



        for (QuestionBank questionBank : seletedQuestion) {
            List<QuestionBankOption> questionBankOptionList = questionBank.getQuestionBankOptionList();

            Answer anwser = new Answer();
            anwser.setReftestno(testno);
            anwser.setRefqbankno(questionBank.getQbankno());

            List<QuestionBankOption> questionBankOptionListNew = new ArrayList<>();

            if("text".equals(questionBank.getTestType().getTesttype())){
                anwser.setOporder("none");
            }else {
                List<String> stringList = Arrays.asList("0123".split(""));
                Collections.shuffle(stringList);
                String listString = stringList.stream().map(Object::toString)
                        .collect(Collectors.joining(""));


                for (String s : stringList) {
                    int i = Integer.parseInt(s);
                    questionBankOptionListNew.add(questionBankOptionList.get(i));
                    anwser.setOporder(listString);
                }

                questionBank.setQuestionBankOptionList(questionBankOptionListNew);
            }

            anwserList.add(anwser);
        }


        anwserMapper.insertAnwserList(anwserList);


        return seletedQuestion;
    }

    public void  correctTestPapper(HttpServletRequest request, String testno){
        Enumeration<String> enums = request.getParameterNames();
//        List<Answer> answerList = new ArrayList<>();
        Map map = new HashMap();
        map.put("reftestno", testno);
        List<Answer> anwserList = anwserMapper.findAnwserListByCondition(map);

        int j = 0;
        while (enums.hasMoreElements()) {
            String name = (String) enums.nextElement();

            if("testno".equals(name)){
                continue;
            }

            Answer ans = anwserList.get(j);
            String value = request.getParameter(name);

            logger.info(value);
            ans.setStudentans(value);
            j++;
        }
        anwserMapper.updateAnwserList(anwserList);
    }

}
