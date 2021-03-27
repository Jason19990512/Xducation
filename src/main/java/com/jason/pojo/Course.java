package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private String courseno;
    private String refcstypeno;
    private String reftchrno;
    private String coursename;
    private String courseinfo;
    private Integer courseprice;
    private Integer ttltime;
    private String csstatus;
    private Integer csscore;
    private Integer csscoretimes;
    private String courseimg;
    private Timestamp courlmod;
}
