package org.minimymav.repository;

import org.minimymav.entity.Enrollment;
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
    public void delete(Enrollment enrollment) {
        em.detach(enrollment);
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
         em.persist(enrollment);
         return enrollment;
    }


}
