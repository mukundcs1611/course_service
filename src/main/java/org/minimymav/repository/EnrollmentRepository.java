package org.minimymav.repository;

import org.minimymav.entity.Enrollment;

public interface EnrollmentRepository {
    Enrollment findOne(String enrollmentId);
    void delete(Enrollment enrollment);
    Enrollment create(Enrollment enrollment);
}
