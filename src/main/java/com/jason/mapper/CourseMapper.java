package com.jason.mapper;

import com.jason.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CourseMapper {

    public List<Course> getCourseByCondition(Map map);

    public Course findCourseByPrimaryKey(String courseno);

    public List<Course> getBestCourse();


    public String insertCourse(Course course);

    public void updateCourse(Course course);


}
