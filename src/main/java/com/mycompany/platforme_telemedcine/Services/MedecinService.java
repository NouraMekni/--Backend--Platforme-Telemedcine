package com.mycompany.platforme_telemedcine.Services;

import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Models.Patient;

import java.util.List;

public interface MedecinService {
    Medecin createMedecin(Medecin m);
    Medecin updateMedecin(Medecin m);
    void deleteMedecinById(Long id);
    Medecin getMedecinById(Long id);
    List<Medecin> getAllMedecin();

    Medecin getMedecinByEmail(String email);

    void addPatientToMedecin(Long medecinId, Long patientId);
    void removePatientFromMedecin(Long medecinId, Long patientId);
    List<Patient> getPatientsByMedecin(Long medecinId);
}
