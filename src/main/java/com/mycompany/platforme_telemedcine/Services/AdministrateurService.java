package com.mycompany.platforme_telemedcine.Services;

import com.mycompany.platforme_telemedcine.Models.Administrateur;

public interface AdministrateurService {
    Administrateur createAdministrateur(Administrateur administrateur);
    Administrateur updateAdministrateur(Administrateur administrateur);
    Administrateur getAdministrateurById(Long id);
    void deleteAdministrateurById(Long id);

    Administrateur getAdminByEmail(String email);
}
