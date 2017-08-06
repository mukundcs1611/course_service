package org.minimymav.controller;

import org.minimymav.entity.Course;
import org.minimymav.entity.Enrollment;
import org.minimymav.service.CourseService;
import org.minimymav.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author chavali mukund
 */

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value="donotAccess")
    public @ResponseBody List<Course> findAll(){
       Course c1=new Course();
       c1.setCourseNo(1234);
       c1.setEnrolled(12);
       Course c2=new Course();
       c2.setCourseNo(12345);
       c2.setEnrolled(25);
        //return "hello";
        return Arrays.asList(c1,c2);
    }
    @CrossOrigin
    @PostMapping(value="view",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List<Course> findFiltered(@RequestBody Map<String,String> params){
        return courseService.findFiltered(params);
    }

    @PutMapping(value="{id}")
    public @ResponseBody Course update(@PathVariable String id,@RequestBody Course course){
        return courseService.updateCourse(id,course);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Course create(@RequestBody Course course){
        return courseService.create(course);
    }

    @PutMapping()
    public @ResponseBody Enrollment enroll(@RequestBody String uuid, @RequestBody String user){
        return courseService.enroll(uuid,user);
    }
    @DeleteMapping()
    public @ResponseBody boolean drop(@RequestBody String uuid,@RequestBody String enrollmentId){
        return courseService.drop(uuid,enrollmentId);
    }
}

