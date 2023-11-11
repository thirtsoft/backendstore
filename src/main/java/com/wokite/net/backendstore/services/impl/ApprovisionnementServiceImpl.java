package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Approvisionnement;
import com.wokite.net.backendstore.models.LigneApprovisionnement;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.ApprovisionnementRepository;
import com.wokite.net.backendstore.services.ApprovisionnementService;
import com.wokite.net.backendstore.services.LigneApprovisionnementService;
import com.wokite.net.backendstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApprovisionnementServiceImpl implements ApprovisionnementService {

    @Autowired private ApprovisionnementRepository approvisionnementRepository;

    @Autowired private LigneApprovisionnementService ligneApprovisionnementService;

    @Autowired private ProductService productService;

    @Override
    public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) {
        approvisionnementRepository.save(approvisionnement);
        List<LigneApprovisionnement> ligneApprovisionnements = approvisionnement.getLigneApprovisionnements();

        if (ligneApprovisionnements == null || ligneApprovisionnements.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins selectionner un article");
        }

        if ((approvisionnement.getFournisseur().getId() == null)) {
            throw new IllegalArgumentException("Vous devez selectionner un fournisseur");
        }

        double total = 0;

        for (LigneApprovisionnement lAppro : ligneApprovisionnements) {
            lAppro.setApprovisionnement(approvisionnement);
            lAppro.setNumeroligneApprovisionnement(approvisionnement.getCode());

            ligneApprovisionnementService.saveLigneApprovisionnement(lAppro);

            Product product = productService.findProductById(lAppro.getProduct().getId()).get();
            if (product != null) {
                product.setQtestock(product.getQtestock() + lAppro.getQuantite());
                productService.updateProduct(product.getId(), product);
            }

            lAppro.setPrix(product.getPrixAchat());

            total += (lAppro.getQuantite() * lAppro.getPrixLigneAppro());

        }

        approvisionnement.setTotalAppro(total);
        approvisionnement.setStatus("ENCOURS");
        approvisionnement.setDateApprovisionnement(new Date());

        return approvisionnementRepository.save(approvisionnement);
    }

    @Override
    public Approvisionnement updateApprovisionnement(Long approId, Approvisionnement approvisionnement) {
        if (!approvisionnementRepository.existsById(approId)) {
            throw new ResourceNotFoundException("Approvisionnement Not found");
        }
        Optional<Approvisionnement> Appro = approvisionnementRepository.findById(approId);
        if (!Appro.isPresent()) {
            throw new ResourceNotFoundException("Approvisionnement Not found");
        }

        Approvisionnement ApproResult = Appro.get();
        ApproResult.setCode(approvisionnement.getCode());
        ApproResult.setStatus(approvisionnement.getStatus());
        ApproResult.setFournisseur(approvisionnement.getFournisseur());
        ApproResult.setDateApprovisionnement(approvisionnement.getDateApprovisionnement());

        return approvisionnementRepository.save(ApproResult);
    }

    @Override
    public Approvisionnement updateStatusAppro(String status, String id) {
        Optional<Approvisionnement> originalAppro = approvisionnementRepository.findById(Long.valueOf(id));
        Approvisionnement approvisionnement = originalAppro.get();
        approvisionnement.setStatus(status);
        return approvisionnementRepository.save(approvisionnement);
    }

    @Override
    public Approvisionnement updateMontantAvanceAppro(double montantAvance, String id) {
        Optional<Approvisionnement> originalAppro = approvisionnementRepository.findById(Long.valueOf(id));
        Approvisionnement approvisionnement = originalAppro.get();
        approvisionnement.setMontantAvance(montantAvance);
        return approvisionnementRepository.save(approvisionnement);
    }

    @Override
    public Optional<Approvisionnement> findApprovisionnementById(Long approId) {
        if (!approvisionnementRepository.existsById(approId)) {
            throw new ResourceNotFoundException("Approvisionnement Not found");
        }
        return approvisionnementRepository.findById(approId);
    }

    @Override
    public Approvisionnement findApprovisionnementByCode(Long code) {
        return approvisionnementRepository.findByCode(code);
    }

    @Override
    public List<Approvisionnement> findAllApprovisionnements() {
        return approvisionnementRepository.findAll();
    }

    @Override
    public List<Approvisionnement> findAllApprovisionnementsOrderDesc() {
        return approvisionnementRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Approvisionnement> findListApprovisionnementOf3LatestMonth() {
        return approvisionnementRepository.findListApprovisionnementOf3LatestMonth();
    }

    @Override
    public List<Approvisionnement> findTop500OApprovisionnementOrderByIdDesc() {
        return approvisionnementRepository.findTop500ByOrderByIdDesc();
    }

    @Override
    public List<Approvisionnement> findListApprovisionnementByFournisseurId(Long fourId) {
        return approvisionnementRepository.findListApprovisionnementByFournisseurId(fourId);
    }


    @Override
    public void deleteAppro(Long id) {
        Optional<Approvisionnement> approInfo = approvisionnementRepository.findById(id);
        if (approInfo.isPresent()) {
            Approvisionnement approvisionnement = approInfo.get();
            approvisionnement.setActif(false);
            approvisionnementRepository.save(approvisionnement);
            List<LigneApprovisionnement> ligneApprovisionnements = approvisionnement.getLigneApprovisionnements();

            /*
            for (LigneApprovisionnement lAppro : ligneApprovisionnements) {

            }*/
            ligneApprovisionnementService.deleteLApproByNumero(approvisionnement.getCode());
        //    approvisionnementRepository.delete(approvisionnement);
        }
    }
}
