package com.jason.mapper;

import com.jason.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    public List<Teacher> getAllTeacher();

    public Teacher findTeacherByPrimaryKey(String tchrno);

    public Teacher getOneTeacherByMemno(String memno);

    public void insertTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void updateTeacherStatus(String tchrno,String tchrstatus,String rejreason);



}
