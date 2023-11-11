package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Versement;
import com.wokite.net.backendstore.repository.VersementRepository;
import com.wokite.net.backendstore.services.VersementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VersementServiceImpl implements VersementService {

    private final VersementRepository versementRepository;

    public VersementServiceImpl(VersementRepository versementRepository) {
        this.versementRepository = versementRepository;
    }

    @Override
    public Versement saveVersement(Versement versement) {
        if (versement.getNumVersement() != null) {
            throw new ResourceNotFoundException("Versement exist");
        }
        versement.setDateVersement(new Date());
        return versementRepository.save(versement);
    }

    @Override
    public Versement updateVersement(Long id, Versement versement) {
        if (!versementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Versement Not found");
        }
        Optional<Versement> optionalVersement = versementRepository.findById(id);
        if (!optionalVersement.isPresent()) {
            throw new ResourceNotFoundException("Versment Not found");
        }

        Versement versementResult = optionalVersement.get();

        versementResult.setNumVersement(versement.getNumVersement());
        versementResult.setNumeroRecu(versement.getNumeroRecu());
        versementResult.setMontantVersement(versement.getMontantVersement());
        versementResult.setMotif(versement.getMotif());
        versementResult.setDateVersement(versement.getDateVersement());
        versementResult.setEmploye(versement.getEmploye());

        return versementRepository.save(versementResult);
    }

    @Override
    public Optional<Versement> findVersementById(Long id) {
        if (!versementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Versement Not found");
        }
        return versementRepository.findById(id);
    }

    @Override
    public List<Versement> findAllVersements() {
        return versementRepository.findAll();
    }

    @Override
    public List<Versement> findAllVersementsOrderDesc() {
        return versementRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Versement> findVersementByEmployeId(Long empId) {
        return versementRepository.findVersementByEmployeId(empId);
    }

    @Override
    public void deleteVersement(Long id) {
        if (!versementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Versement Not found");
        }
        Versement versement = versementRepository.findById(id).get();
        versement.setActif(false);
        versementRepository.save(versement);
    }
}
