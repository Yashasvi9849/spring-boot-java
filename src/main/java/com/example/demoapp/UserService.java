package com.example.demoapp;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userRepository.save(user);

        return true;
    }


}
