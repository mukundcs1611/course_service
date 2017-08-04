package org.minimymav.controller;

import org.minimymav.entity.Course;
import org.minimymav.service.CourseService;
import org.minimymav.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="course")

public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(method= RequestMethod.GET,value="donotAccess")
    public List<Course> findAll(){
       Course c1=new Course();
       c1.setCourseNo(1234);
       c1.setEnrolled(12);
       Course c2=new Course();
       c2.setCourseNo(12345);
       c2.setEnrolled(25);
        //return "hello";
        return Arrays.asList(c1,c2);
    }

    @RequestMapping(method=RequestMethod.POST,value="view",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<Course> findFiltered(@RequestBody Map<String,String> params){
        return courseService.findFiltered(params);
    }

    @RequestMapping(method=RequestMethod.PUT,value="{id}")
    public @ResponseBody Course update(@RequestParam String id,@RequestBody Course course){
        return courseService.updateCourse(id,course);
    }

    @RequestMapping(method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Course create(@RequestBody Course course){
        return courseService.create(course);
    }
}

