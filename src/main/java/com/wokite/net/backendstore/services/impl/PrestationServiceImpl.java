package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Prestation;
import com.wokite.net.backendstore.repository.PrestationRepository;
import com.wokite.net.backendstore.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrestationServiceImpl implements PrestationService {

    @Autowired private PrestationRepository prestationRepository;

    @Override
    public Prestation savePrestation(Prestation prestation) {
        return prestationRepository.save(prestation);
    }

    @Override
    public Prestation updatePrestation(Long prestId, Prestation prestation) {
        if (!prestationRepository.existsById(prestId)) {
            throw new ResourceNotFoundException("Prestation that id is" + prestId + "not found");
        }
        Optional<Prestation> optionalPrestation = prestationRepository.findById(prestId);

        if (!optionalPrestation.isPresent()) {
            throw new ResourceNotFoundException("Prestation that id is" + prestId + "not found");
        }
        Prestation prestationResult = optionalPrestation.get();

        prestationResult.setDesignation(prestation.getDesignation());
        prestationResult.setMontant(prestation.getMontant());
        prestationResult.setDatePrestation(new Date());

        return prestationRepository.save(prestationResult);
    }

    @Override
    public Optional<Prestation> findPrestationById(Long prestId) {
        if (!prestationRepository.existsById(prestId)) {
            throw new ResourceNotFoundException("Prestation that id is" + prestId + "not found");
        }
        return prestationRepository.findById(prestId);
    }

    @Override
    public List<Prestation> findAllPrestations() {
        return prestationRepository.findAll();
    }

    @Override
    public List<Prestation> findAllPrestationsOrderDesc() {
        return prestationRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal sumTotalOfPrestationInMonth() {
        return prestationRepository.sumTotalOfPrestationByMonth();
    }

    @Override
    public BigDecimal sumTotalOfPrestationInYear() {
        return prestationRepository.sumTotalOfPrestationByYear();
    }

    @Override
    public void deletePrestation(Long prestId) {
        if (!prestationRepository.existsById(prestId)) {
            throw new ResourceNotFoundException("Prestation that id is" + prestId + "not found");
        }
        prestationRepository.deleteById(prestId);
    }
}
