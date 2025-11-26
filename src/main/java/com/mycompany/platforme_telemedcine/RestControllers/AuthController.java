package com.mycompany.platforme_telemedcine.RestControllers;
import com.mycompany.platforme_telemedcine.Models.Administrateur;
import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Services.AdministrateurService;
import com.mycompany.platforme_telemedcine.Services.MedecinService;
import com.mycompany.platforme_telemedcine.dto.LoginRequest;
import com.mycompany.platforme_telemedcine.Models.Patient;
import com.mycompany.platforme_telemedcine.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private AdministrateurService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Patient patient = patientService.getPatientByEmail(email);
        if (patient != null && patient.getPassword().equals(password)) {
            return ResponseEntity.ok(patient);
        }

        Medecin medecin = medecinService.getMedecinByEmail(email);
        if (medecin != null && medecin.getPassword().equals(password)) {
            return ResponseEntity.ok(medecin);
        }

        Administrateur admin = adminService.getAdminByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return ResponseEntity.ok(admin);
        }

        return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
    }
}
