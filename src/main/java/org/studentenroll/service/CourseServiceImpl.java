package org.studentenroll.service;

import org.studentenroll.Exception.BadRequestException;
import org.studentenroll.Exception.NotFoundException;
import org.studentenroll.entity.Course;
import org.studentenroll.entity.Enrollment;
import org.studentenroll.repository.CourseRepository;
import org.studentenroll.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    final
    CourseRepository repository;
    final
    EnrollmentRepository enrollmentRep;

    @Autowired
    public CourseServiceImpl(CourseRepository repository, EnrollmentRepository enrollmentRep) {
        this.repository = repository;
        this.enrollmentRep = enrollmentRep;
    }

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
    @Transactional
    public List<Course> findFiltered(Map<String,String> params) {
        //TODO  very big select statement , refactor

        String query="SELECT course FROM Course course WHERE course.subject='"+params.get("subject")+"'";
        if(params.containsKey("courseNum")){
            String option=params.get("coption");
            query+=" AND course.courseNo "+option+" "+params.get("courseNum")+" ";
        }

        if(params.get("courseLevel")==null){

            if(Objects.equals(params.get("courseLevel"), "G")){
                query+=" AND  course.courseNo >= 5000";
            }

            else if(params.get("courseLevel")!=null)
                query+="AND  course.courseNo <=5000";

        }



        List<Course> courses=new ArrayList<Course>();
        for(Course c:repository.findFiltered(query)){
            Course c2=new Course();
            c2.setCourseNo(c.getCourseNo());
            c2.setEnrollment(null);
            c2.setEnrolled(c.getEnrolled());
            c2.setCourse_title(c.getCourse_title());
            c2.setDays(c.getDays());
            c2.setEnd_time(c.getEnd_time());
            c2.setStart_time(c.getEnd_time());
            c2.setId(c.getId());
            c2.setInstructor(c.getInstructor());
            c2.setSection(c.getSection());
            c2.setMax(c.getMax());
            c2.setSubject(c.getSubject());
            c2.setSemesterId(c.getSemesterId());
            courses.add(c2);
        }
       return courses;

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
    @Transactional
    public Enrollment enroll(String uuid, String useruid) {
        Course course=findOne(uuid);
        Enrollment enrollClass;

        if(course.getMax()>course.getEnrolled()){
            enrollClass=new Enrollment();
            enrollClass.setUserId(useruid);

            course.setEnrolled(course.getEnrolled()+1);//TODO Increment this on success cart submit
            Set<Enrollment> enrolled=course.getEnrollment();
            enrolled.add(enrollClass);
            return enrollmentRep.create(enrollClass);
        }
        return null;
    }
    @Override
    @Transactional
    public boolean drop(String uuid,String enrollmentId){
        Course course=findOne(uuid);
        Enrollment enrollClass=enrollmentRep.findOne(enrollmentId);
        if(course.getEnrollment().contains(enrollClass)){

            course.getEnrollment().remove(enrollClass);
            repository.updateCourse(course);
            enrollmentRep.delete(enrollClass);
            return true;
        }

        return false;
    }

    @Override
    public Course findOne(String id){
        return repository.findOne(id);
    }
}
