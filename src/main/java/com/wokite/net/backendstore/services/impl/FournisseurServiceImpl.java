package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Fournisseur;
import com.wokite.net.backendstore.repository.FournisseurRepository;
import com.wokite.net.backendstore.services.FournisseurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
        String codeFournisseur = String.valueOf(fournisseurRepository.findByCode(fournisseur.getCode()));
        if (codeFournisseur==fournisseur.getCode()){
            throw new ResourceNotFoundException("Fournisseur with code " + fournisseur.getCode() + " exist");
        }
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public Fournisseur updateFournisseurt(Long fourId, Fournisseur fournisseur) {
        if (!fournisseurRepository.existsById(fourId)) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(fourId);
        if (!optionalFournisseur.isPresent()) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
        Fournisseur fournisseurResult = optionalFournisseur.get();
        fournisseurResult.setCode(fournisseur.getCode());
        fournisseurResult.setRaisonSocial(fournisseur.getRaisonSocial());
        fournisseurResult.setNumeroCompte(fournisseur.getNumeroCompte());
        fournisseurResult.setAddress(fournisseur.getAddress());
        fournisseurResult.setTelephone(fournisseur.getTelephone());
        fournisseurResult.setMobile(fournisseur.getMobile());
        fournisseurResult.setFax(fournisseur.getFax());
        fournisseurResult.setEmail(fournisseur.getEmail());

        return fournisseurRepository.save(fournisseurResult);
    }

    @Override
    public Optional<Fournisseur> findFournisseurById(Long fourId) {
        if (!fournisseurRepository.existsById(fourId)) {
            throw new ResourceNotFoundException("Fournisseur that id is" + fourId + "not found");
        }
        return fournisseurRepository.findById(fourId);
    }

    @Override
    public Integer countNumberOfFournisseurs() {
        return fournisseurRepository.countNumberOfFournisseurs();
    }

    @Override
    public List<Fournisseur> findAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Override
    public List<Fournisseur> findAllFournisseursOrderDesc() {
        return fournisseurRepository.findByOrderByIdDesc();
    }

    @Override
    public void deleteFournisseur(Long fourId) {
        if (fourId == null) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
        Fournisseur fournisseur = fournisseurRepository.findById(fourId).get();
        fournisseur.setActif(false);
        fournisseurRepository.save(fournisseur);

    }
}
