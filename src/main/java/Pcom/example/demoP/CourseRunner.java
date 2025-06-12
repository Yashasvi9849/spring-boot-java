package Pcom.example.demoP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CourseRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public CourseRunner(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
