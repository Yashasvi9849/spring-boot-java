package com.example.demoapp;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public boolean validateCredentials(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt.isPresent() && adminOpt.get().getPassword().equals(password);
    }

    public int loadAdminsFromFile() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("users.csv");
        if (is == null) {
            throw new Exception("users.csv not found in resources.");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        int loadedCount = 0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String username = parts[0].trim();
                String password = parts[1].trim();

                Optional<Admin> existingAdmin = adminRepository.findByUsername(username);
                if (existingAdmin.isEmpty()) {
                    Admin newAdmin = new Admin();
                    newAdmin.setUsername(username);
                    newAdmin.setPassword(password);
                    adminRepository.save(newAdmin);
                    loadedCount++;
                }
            }
        }
        reader.close();
        return loadedCount;
    }
}
