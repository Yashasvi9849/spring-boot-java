package com.example.demoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CourseRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final ConfigurableApplicationContext context;

    public CourseRunner(CourseRepository courseRepository, ConfigurableApplicationContext context) {
        this.courseRepository = courseRepository;
        this.context = context;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------ COURSE REGISTRATION ------");

        while (true) {
            System.out.print("Enter course name (or type 'exit' to stop): ");
            String courseName = scanner.nextLine().trim();

            if (courseName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting course input.");

                // ✅ Graceful Spring Boot shutdown
                SpringApplication.exit(context, () -> 0);
                break;
            }

            System.out.print("Enter professor name: ");
            String professorName = scanner.nextLine().trim();

            Optional<Course> existing = courseRepository.findByCourseNameAndProfessorName(courseName, professorName);

            if (existing.isPresent()) {
                System.out.println(" Course already exists: " + courseName + " by " + professorName);
            } else {
                Course course = new Course(courseName, professorName);
                courseRepository.save(course);
                System.out.println(" Course added: " + courseName + " by Prof. " + professorName);
            }
        }

        System.out.println("Course input finished.");
    }
}
