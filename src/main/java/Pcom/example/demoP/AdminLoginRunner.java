package Pcom.example.demoP;



import Pcom.example.demoP.Admin;
import Pcom.example.demoP.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class AdminLoginRunner implements CommandLineRunner {

    private final AdminRepository adminRepository;

    public AdminLoginRunner(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ADMIN LOGIN VIA CLI");

        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();

        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        Admin admin;

        if (optionalAdmin.isEmpty()) {
            System.out.println("Admin not found. Do you want to register as a new admin? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Set a password: ");
                String newPassword = scanner.nextLine().trim();
                admin = new Admin(username, newPassword);
                adminRepository.save(admin);
                System.out.println("Admin registered successfully.");
            } else {
                System.out.println("Exiting...");
                return;
            }
        } else {
            admin = optionalAdmin.get();
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();

            if (!admin.getPassword().equals(password)) {
                System.out.println("Incorrect password.");
                return;
            }

            System.out.println("Admin login successful.");
        }
    }
}
