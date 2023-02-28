package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeService {

    Employe saveEmploye(Employe employe);

    Employe updateEmploye(Long empId, Employe employe);

    Optional<Employe> findEmployeById(Long empId);

    List<Employe> findAllEmploye();

    List<Employe> findAllEmployesOrderDesc();

    void deleteEmploye(Long empId);

}
