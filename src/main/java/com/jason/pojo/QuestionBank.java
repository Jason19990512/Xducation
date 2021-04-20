package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBank implements java.io.Serializable{
    private Integer qbankno;
    private Integer refcourseno;
    private TestType testType;
//    private Integer reftesttypeno;
    private Integer refmemno;
    private String testscope;
    private String qustmt;
    private boolean qustatus;
    private List<QuestionBankOption> questionBankOptionList;
}
