package org.minimymav.repository;

import org.minimymav.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        TypedQuery<Course> q=em.createQuery(query,Course.class);
        List<Course> result=q.getResultList();
        return result;
    }

    @Override
    public Course updateCourse(Course course) {
        return null;
    }

    @Override
    public void deleteCourse(String id) {

    }
}
