package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements java.io.Serializable{
    private Integer ansno;
    private Integer reftestno;
    private Integer refqbankno;
    private String  oporder;
    private String  studentans;
}
