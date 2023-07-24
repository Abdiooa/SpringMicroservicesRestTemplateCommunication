package com.microspring.departementservice.repositories;

import com.microspring.departementservice.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Long> {
    boolean existsByDepartmentName(String departmentName);
}
