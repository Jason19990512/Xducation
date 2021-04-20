package com.jason.mapper;

import com.jason.pojo.QuestionBankOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface QuestionBankOptionMapper {
    List<QuestionBankOption> findQuestionBankOptionByRefqbankNo(List<String> refqbanknoList);

    void updateQuestionBankOption(QuestionBankOption questionBankOption);

    void insertQuestionBankOption(List<QuestionBankOption> questionBankOption);
}
