package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.EmployeApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Employe;
import com.wokite.net.backendstore.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeController implements EmployeApi {

    @Autowired
    private EmployeService employeService;

    @Override
    public ResponseEntity<Employe> createEmploye(Employe employe) {
        Employe employeResult = employeService.saveEmploye(employe);
        return new ResponseEntity<>(employeResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Employe> updateEmploye(Long empId, Employe employe) {
        employe.setId(empId);
        Employe employeResult = employeService.saveEmploye(employe);
        return new ResponseEntity<>(employeResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employe> getEmployeById(Long id) {
        Employe employeResult = employeService.findEmployeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe that id is" + id + "not found"));
        return new ResponseEntity<>(employeResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employe>> getAllEmployees() {
        List<Employe> employeList = employeService.findAllEmploye();
        return new ResponseEntity<>(employeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employe>> getAllEmployeesOrderDesc() {
        List<Employe> employeList = employeService.findAllEmployesOrderDesc();
        return new ResponseEntity<>(employeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteEmploye(Long id) {
        employeService.deleteEmploye(id);
        return ResponseEntity.ok().build();
    }
}
