package com.jason.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.grid.CourseGrid;
import com.jason.mapper.CourseMapper;
import com.jason.mapper.CourseTypeMapper;
import com.jason.pojo.Course;
import com.jason.pojo.CourseType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/toCourseSearch", method = { RequestMethod.GET, RequestMethod.POST } )
    public String CourseSearch(Model model, @RequestParam(value="searchText" ,required = false) String searchText){

        HashMap<String ,Object>  map = new HashMap<>();
        if(!StringUtils.isEmpty(searchText)){
            map.put("searchText",searchText);
        }

        List<Course> SearchedCourse = courseMapper.getCourseByCondition(map);


        List<CourseType> allCourseType = courseTypeMapper.getAllCourseType();
        model.addAttribute("CourseGrid",new CourseGrid());
        model.addAttribute("SearchedCourse",SearchedCourse);
        model.addAttribute("allCourseType",allCourseType);
        return "front-end/course/courseSearch";
    }

    @RequestMapping(value = "/searchCourse", method = {  RequestMethod.POST } )
    @ResponseBody
    public String searchCourse(@ModelAttribute("CourseGrid")CourseGrid courseGrid , Model model){
        logger.info(courseGrid.getOrder());
        HashMap<String ,Object>  map = new HashMap<>();
        if(!StringUtils.isEmpty(courseGrid.getSearchText())){
            map.put("searchText",courseGrid.getSearchText());
        }else if(!StringUtils.isEmpty(courseGrid.getRefcstypeno())){
            map.put("refcstypeno",courseGrid.getRefcstypeno());
        }else if (!StringUtils.isEmpty(courseGrid.getOrder())){

            map.put("term",courseGrid.getOrder());
        }

        List<Course> SearchedCourse = courseMapper.getCourseByCondition(map);
        for (Course course : SearchedCourse) {
            System.out.println(course);
        }

//        ObjectMapper mapper = new ObjectMapper();
        return "";
    }

}
