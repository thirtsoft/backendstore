package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Devis;
import com.wokite.net.backendstore.models.LigneDevis;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.DevisRepository;
import com.wokite.net.backendstore.services.DevisService;
import com.wokite.net.backendstore.services.LigneDevisService;
import com.wokite.net.backendstore.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DevisServiceImpl implements DevisService {

    Logger logger = LoggerFactory.getLogger(DevisServiceImpl.class);

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private LigneDevisService ligneDevisService;

    @Autowired
    private ProductService productService;

    @Override
    public Devis saveDevis(Devis devis) {
        logger.info("Devis {}", devis);
        List<LigneDevis> ligneDevisList = devis.getLdevis();
        if (ligneDevisList == null || ligneDevisList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins commander un article");
        }
        if ((devis.getClient().getId() == null)) {
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }

        for (LigneDevis ligneDevis : ligneDevisList) {
            Product productInitial = productService.findProductById(ligneDevis.getProduct().getId()).get();
            if (ligneDevis.getQuantite() > productInitial.getQtestock()) {
                throw new IllegalArgumentException("La Quantité de stock du article est insuffusante");
            }
        }

        devisRepository.save(devis);

        List<LigneDevis> ligneDevisListClients = devis.getLdevis();
        double total = 0;
        for (LigneDevis ligneDevis : ligneDevisListClients) {
            ligneDevis.setDevis(devis);
            ligneDevis.setNumeroligneDevis(devis.getNumeroDevis());
            ligneDevisService.saveLigneDevis(ligneDevis);

            Product product= productService.findProductById(ligneDevis.getProduct().getId()).get();

            ligneDevis.setPrix(product.getPrixVente());

            total += (ligneDevis.getQuantite() * ligneDevis.getPrixligneDevis());

        }

        devis.setTotalDevis(total);
        devis.setStatus("valider");
        devis.setDateDevis(new Date());

        return devisRepository.save(devis);

    }

    @Override
    public Devis updateDevis(Long devId, Devis devis) {
        if (!devisRepository.existsById(devId)) {
            throw new ResourceNotFoundException("Devis not found");
        }

        Optional<Devis> devisClient = devisRepository.findById(devId);

        if (!devisClient.isPresent()) {
            throw new ResourceNotFoundException("Devis not found");
        }

        Devis devisResult = devisClient.get();

        devisResult.setNumeroDevis(devis.getNumeroDevis());
        devisResult.setDateDevis(devis.getDateDevis());
        devisResult.setClient(devis.getClient());
        devisResult.setTotalDevis(devis.getTotalDevis());
        devisResult.setStatus(devis.getStatus());

        return devisRepository.save(devisResult);
    }

    @Override
    public Optional<Devis> findDevisById(Long devId) {
        if (!devisRepository.existsById(devId)) {
            throw new ResourceNotFoundException("Devis that not found");
        }
        return devisRepository.findById(devId);
    }

    @Override
    public BigDecimal countNumbersOfDevis() {
        return devisRepository.countNumbersOfDevis();
    }

    @Override
    public int getNombreDevis(Date d1, Date d2) {
        return 0;
    }

    @Override
    public int getNumberOfDevis() {
        return 0;
    }

    @Override
    public List<Devis> findAllDevis() {
        return devisRepository.findAll();
    }

    @Override
    public List<Devis> findAllDevissOrderDesc() {
        return devisRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Devis> findDevisByClientId(Long clientId) {
        return devisRepository.ListDevisByClientId(clientId);
    }

    @Override
    public List<Devis> findDevisByDate(Date dateDevis) {
        return devisRepository.findAllByDateDevis(dateDevis);
    }

    @Override
    public List<?> countNumberTotalOfDevisPeerMonth() {
        return devisRepository.countNumberOfDevisByMonth();
    }

    @Override
    public List<?> sumTotalOfDevisPeerMonth() {
        return devisRepository.sumTotalOfDevisByMonth();
    }

    @Override
    public void deleteDevis(Long id) {
        Optional<Devis> devisInfo = devisRepository.findById(id);
        if (devisInfo.isPresent()) {
            Devis devis = devisInfo.get();
            ligneDevisService.deleteLigneDevisByNumero(devis.getNumeroDevis());
            devisRepository.delete(devis);
        }
    }
}
