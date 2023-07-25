package com.microspring.usersservice.feignInter;

import com.microspring.usersservice.dto.DepartementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:8081")
public interface APIClient {
    @GetMapping(value = "/api/v1/departments/{id}")
    DepartementDto getDepartmentById(@PathVariable("id") Long departmentId);
}
