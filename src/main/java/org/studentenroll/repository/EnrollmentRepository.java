package org.studentenroll.repository;

import org.studentenroll.entity.Enrollment;

public interface EnrollmentRepository {
    Enrollment findOne(String enrollmentId);
    void delete(Enrollment enrollment);
    Enrollment create(Enrollment enrollment);
}
