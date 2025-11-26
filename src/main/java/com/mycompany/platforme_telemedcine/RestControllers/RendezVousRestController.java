package com.mycompany.platforme_telemedcine.RestControllers;

import com.mycompany.platforme_telemedcine.Models.Consultation;
import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Models.Patient;
import com.mycompany.platforme_telemedcine.Models.RendezVous;
import com.mycompany.platforme_telemedcine.Services.ConsultationService;
import com.mycompany.platforme_telemedcine.Services.MedecinService;
import com.mycompany.platforme_telemedcine.Services.PatientService;
import com.mycompany.platforme_telemedcine.Services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousRestController {

    @Autowired
    RendezVousService rendezVousService;

    @Autowired
    PatientService patientService;
    @Autowired
    MedecinService medecinService;
    @Autowired
    ConsultationService consultationService;


    @PostMapping("/add/{patientId}/{medecinId}")
    public ResponseEntity<RendezVous> addRendezVous(
            @RequestBody RendezVous rendezVous,
            @PathVariable Long patientId,
            @PathVariable Long medecinId) {
        try{
            if (rendezVous.getDate() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Patient patient = patientService.getPatientById(patientId);
            Medecin medecin = medecinService.getMedecinById(medecinId);

            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);

            RendezVous saved = rendezVousService.createRendezvous(rendezVous);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/add2/{patientId}/{medecinId}/{consultationId}")
    public ResponseEntity<RendezVous> addRendezVousWithConsultation(
            @RequestBody RendezVous rendezVous,
            @PathVariable Long consultationId, @PathVariable Long patientId, @PathVariable Long medecinId) {
        try{
            Patient patient = patientService.getPatientById(patientId);
            Medecin medecin = medecinService.getMedecinById(medecinId);
            Consultation consultation = consultationService.getConsultationById(consultationId);
            rendezVous.setConsultation(consultation);
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            RendezVous saved = rendezVousService.createRendezvous(rendezVous);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousService.getAllRendezVous();
        if (rendezVousList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVous>> getByPatient(@PathVariable Long patientId) {
        List<RendezVous> list = rendezVousService.getByPatient(patientId);
        return list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.getRendezVousById(id);
        if (rendezVous != null) {
            return new ResponseEntity<>(rendezVous, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        RendezVous existingRendezVous = rendezVousService.getRendezVousById(id);
        if (existingRendezVous == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (rendezVous.getDate() != null) {
            existingRendezVous.setDate(rendezVous.getDate());
        }
        if (rendezVous.getDescription() != null) {
            existingRendezVous.setDescription(rendezVous.getDescription());
        }

        RendezVous updated = rendezVousService.updateRendezVous(existingRendezVous);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRendezVous(@PathVariable Long id) {
        try{
            rendezVousService.deleteRendezVous(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
