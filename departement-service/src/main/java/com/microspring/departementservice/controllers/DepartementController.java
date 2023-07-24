package com.microspring.departementservice.controllers;

import com.microspring.departementservice.dto.DepartementRequest;
import com.microspring.departementservice.dto.DepartementResponse;
import com.microspring.departementservice.exceptions.DepartementNotFoundExceptiom;
import com.microspring.departementservice.exceptions.DepartmentWithThatNameAlreadyExist;
import com.microspring.departementservice.services.DepartementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Validated
@Slf4j
public class DepartementController {
    private final DepartementService departementService;
    @PostMapping("")
    public ResponseEntity<?> createDepartment(@RequestBody DepartementRequest departementRequest){
        try{
            DepartementResponse departementResponse = departementService.saveDepartement(departementRequest);
            return new ResponseEntity<>(departementResponse,HttpStatus.CREATED);
        }catch (DepartmentWithThatNameAlreadyExist departmentWithThatNameAlreadyExist){
            return new ResponseEntity<>("A department with this name is already exists", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id){
        try{
            DepartementResponse departementResponse = departementService.getDepartmentById(id);
            return new ResponseEntity<>(departementResponse,HttpStatus.OK);
        }catch (DepartementNotFoundExceptiom departementNotFoundExceptiom){
            return new ResponseEntity<>("Departement Not found",HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getALlDepartments(){
        try{
            List<DepartementResponse>  departementResponses= departementService.getAllDepartments();
            return new ResponseEntity<>(departementResponses,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
