package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Depense;
import com.wokite.net.backendstore.repository.DepenseRepository;
import com.wokite.net.backendstore.services.DepenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepenseServiceImpl implements DepenseService {

    private final DepenseRepository depenseRepository;

    public DepenseServiceImpl(DepenseRepository depenseRepository) {
        this.depenseRepository = depenseRepository;
    }

    @Override
    public Depense saveCharge(Depense depense) {
        depense.setDateCharge(new Date());
        depense.setActif(true);
        return depenseRepository.save(depense);
    }

    @Override
    public Depense updateCharge(Long idCh, Depense depense) {
        if (!depenseRepository.existsById(idCh)) {
            throw new ResourceNotFoundException("Charge not found");
        }
        Optional<Depense> optionalCharge = depenseRepository.findById(idCh);
        if (!optionalCharge.isPresent()) {
            throw new ResourceNotFoundException("Charge not found");
        }
        Depense depenseResult = optionalCharge.get();
        depenseResult.setDesignation(depense.getDesignation());
        depenseResult.setTypeDepense(depense.getTypeDepense());
        depenseResult.setMontantCharge(depense.getMontantCharge());
        depenseResult.setDateCharge(depense.getDateCharge());

        return depenseRepository.save(depenseResult);
    }

    @Override
    public Optional<Depense> findChargeById(Long idCh) {
        if (!depenseRepository.existsById(idCh)) {
            throw new ResourceNotFoundException("Charge N° " + idCh + "not found");
        }
        return depenseRepository.findById(idCh);
    }


    @Override
    public BigDecimal sumTotalOfChargeInYear() {
        return depenseRepository.sumTotalOfChargeByYear();
    }

    @Override
    public List<Depense> findAllCharges() {
        return depenseRepository.findAll();
    }

    @Override
    public List<Depense> findAllChargesOrderDesc() {
        return depenseRepository.findByOrderByIdDesc();
    }

    @Override
    public List<?> sumTotalOfChargePeerMonth() {
        return depenseRepository.sumTotalOfChargeByMonth();
    }

    @Override
    public void deleteCharge(Long id) {
        if (!depenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Charge not found");
        }
        Depense depense = depenseRepository.findById(id).get();
        depense.setActif(false);
        depenseRepository.save(depense);
    }
}
