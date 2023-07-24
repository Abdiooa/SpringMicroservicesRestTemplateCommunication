package com.microspring.departementservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartementResponse {
    private Long id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
