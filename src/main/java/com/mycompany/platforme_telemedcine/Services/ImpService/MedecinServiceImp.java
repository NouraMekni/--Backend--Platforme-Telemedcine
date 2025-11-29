package com.mycompany.platforme_telemedcine.Services.ImpService;

import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Models.Patient;
import com.mycompany.platforme_telemedcine.Models.User;
import com.mycompany.platforme_telemedcine.Repository.MedecinRepository;
import com.mycompany.platforme_telemedcine.Repository.PatientRepository;
import com.mycompany.platforme_telemedcine.Repository.UserRepository;
import com.mycompany.platforme_telemedcine.Services.MedecinService;
import com.mycompany.platforme_telemedcine.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinServiceImp implements MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Medecin createMedecin(Medecin m) {
        User savedUser = userRepository.save(m);
        return medecinRepository.save(m);
    }

    @Override
    public Medecin updateMedecin(Medecin m) {
        return medecinRepository.save(m);
    }

    @Override
    public void deleteMedecinById(Long id) {
        this.medecinRepository.deleteById(id);
    }

    @Override
    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).get();
    }

    @Override
    public List<Medecin> getAllMedecin() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin getMedecinByEmail(String email) {
        return medecinRepository.findMedecinByEmail(email);
    }

    @Override
    @Transactional
    public void addPatientToMedecin(Long medecinId, Long patientId) {
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        medecin.addPatient(patient);
        medecinRepository.save(medecin);
    }

    @Override
    @Transactional
    public void removePatientFromMedecin(Long medecinId, Long patientId) {
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        medecin.removePatient(patient);
        medecinRepository.save(medecin);
    }

    @Override
    public List<Patient> getPatientsByMedecin(Long medecinId) {
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        return medecin.getPatients();
    }
}
