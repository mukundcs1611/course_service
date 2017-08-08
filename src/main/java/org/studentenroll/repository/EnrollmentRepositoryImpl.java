package org.studentenroll.repository;

import org.springframework.transaction.annotation.Transactional;
import org.studentenroll.entity.Enrollment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Enrollment findOne(String enrollmentId) {
        return em.find(Enrollment.class,enrollmentId);
    }

    @Override
    @Transactional
    public void delete(Enrollment enrollment) {
        em.detach(enrollment);
    }

    @Override
    @Transactional
    public Enrollment create(Enrollment enrollment) {
         em.persist(enrollment);
         return enrollment;
    }


}
