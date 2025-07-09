package com.example.demoapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleAdminLogin(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        System.out.println("Received login attempt for admin: " + username);

        if (adminService.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>("Admin not found. Please contact administrator.", HttpStatus.NOT_FOUND);
        }

        boolean isValid = adminService.validateCredentials(username, password);

        return isValid
                ? new ResponseEntity<>("Admin login successful.", HttpStatus.OK)
                : new ResponseEntity<>("Incorrect password.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/reload-users")
    public ResponseEntity<String> reloadUsersFromFile() {
        try {
            int count = adminService.loadAdminsFromFile();
            return new ResponseEntity<>("Successfully loaded " + count + " new users from file.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error loading users: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
