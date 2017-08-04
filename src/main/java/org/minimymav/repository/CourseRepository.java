package org.minimymav.repository;

import org.minimymav.entity.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CourseRepository {
     List<Course> findAll();
     Course findOne(String id);
     Course create(Course course);
     List<Course> findFiltered(String query);
     Course updateCourse(Course course);
     void deleteCourse(String id);
}
