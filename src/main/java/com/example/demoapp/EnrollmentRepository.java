package com.example.demoapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
    boolean existsByUserAndCourse(User user, Course course);
}
