package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Employe;
import com.wokite.net.backendstore.repository.EmployeRepository;
import com.wokite.net.backendstore.services.EmployeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe saveEmploye(Employe employe) {
        /*
        if ((employeRepository.findByEmail(employe.getEmail()) != null)) {
            throw new IllegalArgumentException("Ce Employe exist");
        }

         */
        return employeRepository.save(employe);
    }

    @Override
    public Employe updateEmploye(Long empId, Employe employe) {
        if (!employeRepository.existsById(empId)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        Optional<Employe> optionalEmploye = employeRepository.findById(empId);
        if (!optionalEmploye.isPresent()) {
            throw new ResourceNotFoundException("Employee not found");
        }

        Employe empResult = optionalEmploye.get();

        empResult.setCni(employe.getCni());
        empResult.setNom(employe.getNom());
        empResult.setPrenom(employe.getPrenom());
        empResult.setAddress(employe.getAddress());
        empResult.setTelephone(employe.getTelephone());
        empResult.setTelephone2(employe.getTelephone2());
        empResult.setEmail(employe.getEmail());

        return employeRepository.save(empResult);

    }

    @Override
    public Optional<Employe> findEmployeById(Long empId) {
        if (!employeRepository.existsById(empId)) {
            throw new ResourceNotFoundException("Employee N° " + empId + "not found");
        }
        return employeRepository.findById(empId);
    }


    @Override
    public List<Employe> findAllEmploye() {
        return employeRepository.findAll();
    }

    @Override
    public List<Employe> findAllEmployesOrderDesc() {
        return employeRepository.findByOrderByIdDesc();
    }

    @Override
    public void deleteEmploye(Long empId) {
        if (!employeRepository.existsById(empId)) {
            throw new ResourceNotFoundException("Employe N° " + empId + "not found");
        }
        Employe employe = employeRepository.findById(empId).get();
        employe.setActif(false);
        employeRepository.save(employe);
    }
}
