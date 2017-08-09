package org.studentenroll.repository;

import org.studentenroll.entity.Course;
import org.studentenroll.entity.Enrollment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseRepositoryImpl implements CourseRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course findOne(String id){
        return em.find(Course.class,id);
    }

    @Override
    public Course create(Course course) {
        em.persist(course);
        return course;
    }

    @Override
    public List<Course> findFiltered(String query) {
        EntityGraph graph=em.getEntityGraph("Courses.noEnrollment");

        TypedQuery<Course> q=em.createQuery(query,Course.class);
        q.setHint("javax.persistance.fetchgraph",graph);

        System.out.println("Are we here");
        List<Course> results=q.getResultList();

        return q.getResultList();
    }

    @Override
    public Course updateCourse(Course course) {
        return null;
    }

    @Override
    public void deleteCourse(String id) {

    }

    @Override
    public Enrollment enroll(Course course) {

        return null;
    }
}
