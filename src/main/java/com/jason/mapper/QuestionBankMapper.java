package com.jason.mapper;

import com.jason.pojo.QuestionBank;

import java.util.List;
import java.util.Map;

public interface QuestionBankMapper {

    List<QuestionBank> findQuestionByConditon(Map map);

    void insertQuestion(QuestionBank question);

    void updateQuestion(QuestionBank question);

}
