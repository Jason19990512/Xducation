package com.jason.mapper;

import com.jason.pojo.QuestionBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionBankMapper {

    List<QuestionBank> findQuestionByConditon(Map map);

    void insertQuestion(QuestionBank question);

    void updateQuestion(QuestionBank question);

}
