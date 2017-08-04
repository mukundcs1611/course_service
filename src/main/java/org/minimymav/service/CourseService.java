package org.minimymav.service;

import org.minimymav.entity.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CourseService {
public List<Course> findAll();
public Course create(Course course);
//public List<Course> findFiltered(String subject,String courseNum, String coption,String courseLevel );
public List<Course> findFiltered(Map<String,String> params);
public Course updateCourse(String id,Course course);
public Course findOne(String id);
public void deleteCourse(String id);
}
