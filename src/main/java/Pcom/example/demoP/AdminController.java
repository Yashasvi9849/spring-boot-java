package Pcom.example.demoP;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/admingit")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleAdminLogin(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        System.out.println("Received login for admin: " + username);

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isEmpty()) {
            Admin newAdmin = new Admin();
            newAdmin.setUsername(username);
            newAdmin.setPassword(password);
            adminRepository.save(newAdmin);
            return new ResponseEntity<>("admin registered successfully",HttpStatus.CREATED);
        }

        Admin admin = adminOpt.get();
        if (admin.getPassword().equals(password)) {
            return new ResponseEntity<>("Admin login successful.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect password.", HttpStatus.NOT_FOUND);
        }
    }
}
