package org.studentenroll.service;

import org.studentenroll.entity.Course;
import org.studentenroll.entity.Enrollment;

import java.util.List;
import java.util.Map;

/**
 * @author mukund,chavali
 */
public interface CourseService {
 List<Course> findAll();
 Course create(Course course);
//public List<Course> findFiltered(String subject,String courseNum, String coption,String courseLevel );
 List<Course> findFiltered(Map<String,String> params);
 Course updateCourse(String id,Course course);
 Course findOne(String id);
 void deleteCourse(String id);
 Enrollment enroll(String uuid, String useruid);
 boolean drop(String uuid,String enrollmentId);
}
