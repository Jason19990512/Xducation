package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBankOption implements java.io.Serializable{
    private Integer qoptionno;
    private Integer refqbankno;
    private String option;
    private String quans;
}
