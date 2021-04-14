package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anwser implements java.io.Serializable{
    private Integer ansno;
    private Integer reftestno;
    private Integer refqbankno;
    private String  opoder;
    private String  studentans;
}
