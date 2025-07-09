package com.example.demoapp;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final CourseService courseService;

    public HomeController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model) {
        String username = authentication.getName();

        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("enrollments", courseService.getEnrollmentsForUser(username));

        return "home"; // maps to home.html
    }

    @PostMapping("/registerCourse")
    public String registerCourse(Authentication authentication,
                                 @RequestParam Long courseId) {
        String username = authentication.getName();
        courseService.enrollUserInCourse(username, courseId);
        return "redirect:/home";
    }
}
