package com.jason.mapper;

import com.jason.pojo.QuestionBankOption;

import java.util.List;

public interface QuestionBankOptionMapper {
    List<QuestionBankOption> findQuestionBankOptionByRefqbankNo(Integer refqbankno);

    void updateQuestionBankOption(QuestionBankOption questionBankOption);

    void insertQuestionBankOption(List<QuestionBankOption> questionBankOption);
}
