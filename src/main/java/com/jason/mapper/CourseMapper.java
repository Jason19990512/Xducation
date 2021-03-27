package com.jason.mapper;

import com.jason.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseMapper {

    public List<Course> getAllCourse();

    public Course findCourseByPrimaryKey(String courseno);

    public List<Course> getBestCourse();


    public String insertCourse(Course course);

    public void updateCourse(Course course);


}
