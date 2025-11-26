package com.mycompany.platforme_telemedcine.Services.ImpService;

import com.mycompany.platforme_telemedcine.Models.RendezVous;
import com.mycompany.platforme_telemedcine.Repository.RendezVousRepository;
import com.mycompany.platforme_telemedcine.Services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousServiceImp implements RendezVousService {
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public RendezVous createRendezvous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public List<RendezVous> getRendezVous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public RendezVous getRendezVousById(Long id) {
        return rendezVousRepository.findRendezVousById(id);
    }

    @Override
    public void deleteRendezVous(Long id) {
        this.rendezVousRepository.deleteById(id);
    }

    @Override
    public RendezVous updateRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public List<RendezVous> getByPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId);
    }

    @Override
    public List<RendezVous> getRendezVousByMedecinId(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId);
    }

}
