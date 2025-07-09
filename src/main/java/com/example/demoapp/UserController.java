package com.example.demoapp;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result.getFieldError().getDefaultMessage());
        }


        boolean isRegistered = userService.registerUser(user);

        if (!isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
