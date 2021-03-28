package com.jason.controller;

import com.jason.mapper.CourseMapper;
import com.jason.mapper.CourseTypeMapper;
import com.jason.pojo.Course;
import com.jason.pojo.CourseType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CourseTypeMapper courseTypeMapper;

    @Autowired
    CourseMapper courseMapper;

    @PostMapping(value = "/toCourseSearch")
    public String CourseSearch(Model model, @RequestParam(value="searchText" ,required = false) String searchText){
        logger.info(searchText);
        Map<String ,Object>  map = new HashMap<>();
        if(!StringUtils.isEmpty(searchText)){
            map.put("searchText",searchText);
        }

        List<Course> SearchedCourse = courseMapper.getCourseByCondition(map);

        for (Course course : SearchedCourse) {
            logger.info(course);
        }
        List<CourseType> allCourseType = courseTypeMapper.getAllCourseType();
        model.addAttribute("allCourseType",allCourseType);
        return "front-end/course/courseSearch";
    }


}
