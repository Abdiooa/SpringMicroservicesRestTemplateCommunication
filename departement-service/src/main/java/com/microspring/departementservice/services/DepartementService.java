package com.microspring.departementservice.services;

import com.microspring.departementservice.dto.DepartementRequest;
import com.microspring.departementservice.dto.DepartementResponse;

import java.util.List;

public interface DepartementService {
    public List<DepartementResponse> getAllDepartments();
    public DepartementResponse saveDepartement(DepartementRequest departementRequest);
    public DepartementResponse getDepartmentById(Long departmentId);
    public void deleteDepartment(Long departmentId);
}
