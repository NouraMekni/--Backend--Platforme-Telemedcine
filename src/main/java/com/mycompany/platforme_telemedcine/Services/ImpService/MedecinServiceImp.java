package com.mycompany.platforme_telemedcine.Services.ImpService;

import com.mycompany.platforme_telemedcine.Models.Medecin;
import com.mycompany.platforme_telemedcine.Repository.MedecinRepository;
import com.mycompany.platforme_telemedcine.Services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinServiceImp implements MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;


    @Override
    public Medecin createMedecin(Medecin m) {
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
}
