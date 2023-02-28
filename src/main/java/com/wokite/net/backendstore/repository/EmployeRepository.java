package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByOrderByIdDesc();


}
