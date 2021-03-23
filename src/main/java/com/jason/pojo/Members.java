package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Members {
    private String memno;
    private String memacc;
    private String mempwd;
    private String memname;
    private String nkname;
    private Date membday;
    private String memail;
    private String mphone;
    private String mprofile;
    private Timestamp regdate;
    private Integer memdelete;
    private String roleList;
}
