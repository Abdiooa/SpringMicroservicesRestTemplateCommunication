package com.microspring.departementservice.services;


import com.microspring.departementservice.dto.DepartementRequest;
import com.microspring.departementservice.dto.DepartementResponse;
import com.microspring.departementservice.exceptions.DepartementNotFoundExceptiom;
import com.microspring.departementservice.exceptions.DepartmentWithThatNameAlreadyExist;
import com.microspring.departementservice.models.Departement;
import com.microspring.departementservice.repositories.DepartementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartementServiceImpl implements DepartementService{
    private final DepartementRepository departementRepository;

    @Override
    public List<DepartementResponse> getAllDepartments() {
        return departementRepository.findAll().stream().map(this::mapToDepartmentResponse).collect(Collectors.toList());
    }
    @Override
    public DepartementResponse saveDepartement(DepartementRequest departementRequest) {
        if(departementRepository.existsByDepartmentName(departementRequest.getDepartmentName())){
            throw new DepartmentWithThatNameAlreadyExist();
        }
        validateInput(departementRequest);
        Departement departement = mapToDepartment(departementRequest);
        departementRepository.save(departement);
        log.info("Department with the id {} is saved",departement.getId());
        return mapToDepartmentResponse(departement);
    }


    @Override
    public DepartementResponse getDepartmentById(Long departmentId) {
        System.out.println("entreeer");
        if(departementRepository.findById(departmentId).isEmpty()){
            throw new DepartementNotFoundExceptiom();
        }
        Optional<Departement> departement = departementRepository.findById(departmentId);
        if(!departement.isPresent()){
            throw new IllegalArgumentException(
                    "Department with this id"+departmentId+" is not found"
            );
        }
        System.out.println("passer tous sa");
        return mapToDepartmentResponse(departement.get());
    }

    @Override
    public void deleteDepartment(Long departementId) {
        if(departementRepository.findById(departementId).isEmpty()){
            throw new DepartementNotFoundExceptiom();
        }
        Optional<Departement> departement = departementRepository.findById(departementId);
        departementRepository.delete(departement.get());
    }
    public void validateInput(DepartementRequest departementRequest){
        if(departementRequest.getDepartmentName() == null || departementRequest.getDepartmentAddress() == null ||
                departementRequest.getDepartmentCode() == null){
            throw new IllegalArgumentException(
                    "Invalid input data."
            );
        }
    }
    private DepartementResponse mapToDepartmentResponse(Departement departement) {
        return DepartementResponse.builder()
                .id(departement.getId())
                .departmentName(departement.getDepartmentName())
                .departmentAddress(departement.getDepartmentAddress())
                .departmentCode(departement.getDepartmentCode())
                .build();
    }
    private Departement mapToDepartment(DepartementRequest departementRequest) {
        return Departement.builder()
                .departmentName(departementRequest.getDepartmentName())
                .departmentAddress(departementRequest.getDepartmentAddress())
                .departmentCode(departementRequest.getDepartmentCode())
                .build();
    }
}
