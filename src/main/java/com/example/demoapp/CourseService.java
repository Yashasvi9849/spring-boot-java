package com.example.demoapp;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         UserRepository userRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // Return all available courses (catalog)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get all courses a user is enrolled in
    public List<Enrollment> getEnrollmentsForUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return (user != null) ? enrollmentRepository.findByUser(user) : List.of();
    }



    public void enrollUserInCourse(String username, Long courseId) {
        User user = userRepository.findByUsername(username).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if(user==null || course==null) return;

        boolean alreadyEnrolled = enrollmentRepository.existsByUserAndCourse(user, course);
        if (!alreadyEnrolled) {
            Enrollment enrollment = new Enrollment(user, course);
            enrollmentRepository.save(enrollment);
        }

    }
}
