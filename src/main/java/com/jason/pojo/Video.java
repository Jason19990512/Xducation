package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements java.io.Serializable{
    private Integer videono;
    private Integer refcourseno;
    private Integer testscope;
    private Integer chapterno;
    private String  chaptername;
    private Integer chapterlen;
    private String videopath;
}
