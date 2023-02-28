package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface FournisseurService {

    Fournisseur saveFournisseur(Fournisseur fournisseur);

    Fournisseur updateFournisseurt(Long fourId, Fournisseur fournisseur);

    Optional<Fournisseur> findFournisseurById(Long fourId);

    Integer countNumberOfFournisseurs();

    List<Fournisseur> findAllFournisseurs();

    List<Fournisseur> findAllFournisseursOrderDesc();

    void deleteFournisseur(Long fourId);
}
