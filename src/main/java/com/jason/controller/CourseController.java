package com.jason.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.grid.CourseGrid;
import com.jason.mapper.CourseMapper;
import com.jason.mapper.CourseTypeMapper;
import com.jason.mapper.VideoMapper;
import com.jason.pojo.Course;
import com.jason.pojo.CourseType;
import com.jason.pojo.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.websocket.server.PathParam;
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

    @Autowired
    VideoMapper videoMapper;

    @RequestMapping(value = "/toCourseSearch", method = { RequestMethod.GET, RequestMethod.POST } )
    public String CourseSearch(Model model, @RequestParam(value="searchText" ,required = false) String searchText, @RequestParam(value = "refCstypeno", required = false) String refCstypeno){

        HashMap<String ,Object>  map = new HashMap<>();
        if(!StringUtils.isEmpty(searchText)){
            map.put("searchText",searchText);
        }
        List<Course> SearchedCourse = courseMapper.getCourseByCondition(map);

        List<CourseType> allCourseType = courseTypeMapper.getAllCourseType();
        model.addAttribute("CourseGrid",new CourseGrid());
        model.addAttribute("SearchedCourse",SearchedCourse);
        model.addAttribute("allCourseType",allCourseType);
        if(!StringUtils.isEmpty(searchText))
            model.addAttribute("searchText",searchText);
        if(!StringUtils.isEmpty(refCstypeno))
            model.addAttribute("refCstypeno",refCstypeno);
        return "front-end/course/courseSearch";
    }

    @RequestMapping(value = "/searchCourse", method = {  RequestMethod.POST,RequestMethod.GET } )
    @ResponseBody
    public String searchCourse(@ModelAttribute("CourseGrid")CourseGrid courseGrid , Model model) throws JsonProcessingException {

        HashMap<String ,Object>  map = new HashMap<>();
        if(!StringUtils.isEmpty(courseGrid.getSearchText())){
            map.put("searchText",courseGrid.getSearchText());
        }else if(!StringUtils.isEmpty(courseGrid.getRefcstypeno())){
            map.put("refcstypeno",courseGrid.getRefcstypeno());
        }else if (!StringUtils.isEmpty(courseGrid.getOrder())){

            map.put("term",courseGrid.getOrder());
        }

        List<Course> SearchedCourse = courseMapper.getCourseByCondition(map);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(SearchedCourse);
        return str;
    }

    @RequestMapping("toCourseMainPage")
    public String toCourseMainPage(@RequestParam("courseno") String courseno, Model model){
        Course course = courseMapper.findCourseByPrimaryKey(courseno);
        CourseType courseType = courseTypeMapper.findCourseTypeByPrimaryKey(course.getRefcstypeno());

        Map map = new HashMap();
        map.put("refcourseno",course.getCourseno());
        List<Video> courseVideoList = videoMapper.getVideoByCondition(map);

        model.addAttribute("course",course);
        model.addAttribute("courseType",courseType);
        model.addAttribute("courseVideoList",courseVideoList);
        return "front-end/course/mainCoursePage";
    }

}
