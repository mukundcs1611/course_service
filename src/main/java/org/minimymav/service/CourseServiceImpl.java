package org.minimymav.service;

import org.minimymav.Exception.BadRequestException;
import org.minimymav.Exception.NotFoundException;
import org.minimymav.entity.Course;
import org.minimymav.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository repository;

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Course create(Course course) {
        if(findOne(course.getId())==null){
            throw new BadRequestException("User already exists ");
        }
        return repository.create(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findFiltered(Map<String,String> params) {

        String query="SELECT course FROM Course course WHERE course.subject='"+params.get("subject")+"'";
        if(params.containsKey("courseNum")){
            String option=params.get("coption");
            query+=" AND course.courseNo "+option+" "+params.get("courseNum")+" ";
        }


        if(params.get("courseLevel").toString()=="G"){
            query+=" AND  course.courseNo >= 5000";
        }
        else
            query+="AND  course.courseNo <=5000";
        System.out.println(query);

       return repository.findFiltered(query);
    }

    @Override
    @Transactional
    public Course updateCourse(String id,Course course) {
        Course courseCheck = findOne(id);
        if(courseCheck==null){
            throw new NotFoundException("User with id"+id+" not found");//return 404
        }
        return repository.updateCourse(course);
    }

    @Override
    @Transactional
    public void deleteCourse(String id) {

    }
    @Override
    public Course findOne(String id){
        return repository.findOne(id);
    }
}
