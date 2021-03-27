package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements java.io.Serializable{
    private String tchrno;
    private String refmemno;
    private String tchrintro;
    private String tchrcert;
    private String tchrstatus;
    private String rejreason;
    private String bankacc;
}
