package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test implements java.io.Serializable{
    private Integer testno;
    private Integer refcourseno;
    private Integer refmemno;
    private Integer testscope;
    private Integer score;
    private Timestamp testtime ;
    private Timestamp finishtime ;
}
