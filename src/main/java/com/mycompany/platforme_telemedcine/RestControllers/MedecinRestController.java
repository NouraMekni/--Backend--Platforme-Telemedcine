package com.mycompany.platforme_telemedcine.RestControllers;

import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Models.Patient;
import com.mycompany.platforme_telemedcine.Models.UserRole;
import com.mycompany.platforme_telemedcine.Services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medecins")
public class MedecinRestController {

    @Autowired
    MedecinService medecinService;

    @PostMapping("/add")
    public ResponseEntity<?> addMedecin(@RequestBody Map<String, Object> requestBody) {
        try {
            if (requestBody == null) {
                return new ResponseEntity<>("Request body is required", HttpStatus.BAD_REQUEST);
            }

            Medecin medecin = new Medecin();

            medecin.setName(requestBody.get("name") != null ? requestBody.get("name").toString() : null);
            medecin.setPrenom(requestBody.get("prenom") != null ? requestBody.get("prenom").toString() : null);
            medecin.setEmail(requestBody.get("email") != null ? requestBody.get("email").toString() : null);
            medecin.setPassword(requestBody.get("password") != null ? requestBody.get("password").toString() : null);
            medecin.setRole(UserRole.MEDECIN);

            if (requestBody.get("specialte") != null) {
                medecin.setSpecialte(requestBody.get("specialte").toString());
            }

            if (requestBody.get("disponibilite") != null) {
                medecin.setDisponibilite(requestBody.get("disponibilite").toString());
            }

            Medecin savedMedecin = medecinService.createMedecin(medecin);

            return new ResponseEntity<>(savedMedecin, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating medecin: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Medecin>> getAllMedecins() {
        List<Medecin> meds = medecinService.getAllMedecin();
        if (meds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(meds, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin med = medecinService.getMedecinById(id);
        if (med != null) {
            return new ResponseEntity<>(med, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{medecinId}/patients/{patientId}")
    public ResponseEntity<?> addPatientToMedecin(
            @PathVariable Long medecinId,
            @PathVariable Long patientId) {
        try {
            System.out.println("➕ Adding patient " + patientId + " to medecin " + medecinId);
            medecinService.addPatientToMedecin(medecinId, patientId);
            return new ResponseEntity<>("Patient added successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("❌ Error adding patient to medecin: " + e.getMessage());
            return new ResponseEntity<>("Error adding patient: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        Medecin med = medecinService.getMedecinById(id);
        if (med != null) {
            medecin.setId(id);
            medecinService.updateMedecin(medecin);
            return new ResponseEntity<>(medecin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedecin(@PathVariable Long id) {
        try {
            medecinService.deleteMedecinById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{medecinId}/patients")
    public ResponseEntity<List<Patient>> getMedecinPatients(@PathVariable Long medecinId) {
        try {
            System.out.println("🔍 Fetching patients for medecin ID: " + medecinId);
            List<Patient> patients = medecinService.getPatientsByMedecin(medecinId);
            System.out.println("📋 Found " + patients.size() + " patients for medecin " + medecinId);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("❌ Error fetching patients for medecin " + medecinId + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{medecinId}/patients/{patientId}")
    public ResponseEntity<?> removePatientFromMedecin(
            @PathVariable Long medecinId,
            @PathVariable Long patientId) {
        try {
            System.out.println("Removing patient " + patientId + " from medecin " + medecinId);
            medecinService.removePatientFromMedecin(medecinId, patientId);
            return new ResponseEntity<>("Patient removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error removing patient from medecin: " + e.getMessage());
            return new ResponseEntity<>("Error removing patient: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
