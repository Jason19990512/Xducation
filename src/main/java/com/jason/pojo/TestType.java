package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestType implements java.io.Serializable{
    private Integer testtypeno;
    private String testtype;
    private String testdgee;
}
