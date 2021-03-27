package com.jason.mapper;

import com.jason.pojo.CourseType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CourseTypeMapper {

    public List<CourseType> getAllCourseType();

    public CourseType findCourseTypeByPrimaryKey(String cstypeno);

    public void insertCourseType(CourseType courseType);

    public void updateCourseType(CourseType courseType);
    public void deleteCourseType(String cstypeno);


}
