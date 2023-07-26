package com.microspring.departementservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartementRequest {
    @NotEmpty
    @Size(min = 2, message = " department name should have at least 2 characters")
    private String departmentName;
    @NotEmpty
    @Size(min = 2, message = " department address should have at least 2 characters")
    private String departmentAddress;
    @NotEmpty
    @Size(min = 2, message = " department code should have at least 2 characters")
    private String departmentCode;
}
