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
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    TestMapper testMapper;

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

    public void  correctTestPapper(HttpServletRequest request, String memacc){

        String testno = request.getParameter("testno");
        String refcourseno = request.getParameter("refcourseno");

        Enumeration<String> enums = request.getParameterNames();
        Map map = new HashMap();
        map.put("reftestno", testno);
        List<Answer> anwserList = anwserMapper.findAnwserListByCondition(map);


        try{
            int j = 0;
            while (enums.hasMoreElements()) {
                String name = (String) enums.nextElement();
                if( "testno".equals(name) || "refcourseno".equals(name) ){
                    continue;
                }

                Answer ans = anwserList.get(j);
                String value = request.getParameter(name);

                ans.setStudentans(value);
                j++;
            }

            anwserMapper.updateAnwserList(anwserList);
        }catch (Exception e){
            logger.debug("answer put error : " + e );
        }
        try{
            Test test = new Test();
            test.setTestno(Integer.parseInt(testno));
            test.setRefcourseno(Integer.parseInt(refcourseno));
            test.setRefmemacc(memacc);

            testMapper.updateTest(test);
        }catch(Exception e){
            logger.debug("answer correct error : " + e );
        }

    }

    @Override
    public List<QuestionBank> getTestResult(HttpServletRequest request) {
        String testno = request.getParameter("testno");

        Map map = new HashMap();
        map.put("testno",testno);
        map.put("review","review");
        map.put("reftestno",testno);

        List<QuestionBank> seletedQuestion = questionBankMapper.findQuestionByConditon(map);

        List<Answer> anwserList = anwserMapper.findAnwserListByCondition(map);

        int i = 0 ;
        for (QuestionBank questionBank : seletedQuestion) {
            List<QuestionBankOption> questionBankOptionList = questionBank.getQuestionBankOptionList();

            List<QuestionBankOption> orderedQuestionBankOptionList = new ArrayList<>();


            if(questionBankOptionList.size() > 2 ){
                String oporderData = anwserList.get(i).getOporder();
                String[] oporder = oporderData.split("");
                for (String s : oporder) {
                    int num = Integer.parseInt(s);
                    orderedQuestionBankOptionList.add(questionBankOptionList.get(num));
                }
            }

            questionBank.setQuestionBankOptionList(orderedQuestionBankOptionList);
            i++;

        }

        return seletedQuestion;
    }

}
