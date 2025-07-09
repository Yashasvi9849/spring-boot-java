package com.example.demoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_WhenAdminExists() {
        Admin admin = new Admin();
        admin.setUsername("admin123");
        when(adminRepository.findByUsername("admin123")).thenReturn(Optional.of(admin));

        Optional<Admin> result = adminService.findByUsername("admin123");

        assertTrue(result.isPresent());
        assertEquals("admin123", result.get().getUsername());
    }

    @Test
    void testFindByUsername_WhenAdminNotExists() {
        when(adminRepository.findByUsername("noadmin")).thenReturn(Optional.empty());

        Optional<Admin> result = adminService.findByUsername("noadmin");

        assertTrue(result.isEmpty());
    }

    @Test
    void testValidateCredentials_Success() {
        Admin admin = new Admin();
        admin.setUsername("admin123");
        admin.setPassword("pass123");

        when(adminRepository.findByUsername("admin123")).thenReturn(Optional.of(admin));

        boolean result = adminService.validateCredentials("admin123", "pass123");

        assertTrue(result);
    }

    @Test
    void testValidateCredentials_InvalidPassword() {
        Admin admin = new Admin();
        admin.setUsername("admin123");
        admin.setPassword("pass123");

        when(adminRepository.findByUsername("admin123")).thenReturn(Optional.of(admin));

        boolean result = adminService.validateCredentials("admin123", "wrong");

        assertFalse(result);
    }

    @Test
    void testValidateCredentials_AdminNotFound() {
        when(adminRepository.findByUsername("admin123")).thenReturn(Optional.empty());

        boolean result = adminService.validateCredentials("admin123", "pass123");

        assertFalse(result);
    }
}
