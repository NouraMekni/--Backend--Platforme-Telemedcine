package com.mycompany.platforme_telemedcine.Repository;

import com.mycompany.platforme_telemedcine.Models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findMedecinById(long id);
    Medecin findMedecinBySpecialte(String specialte);
    Medecin findMedecinByEmail(String email);
}
