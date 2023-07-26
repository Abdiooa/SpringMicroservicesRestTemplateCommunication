package com.microspring.departementservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "departements")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Departement {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "department_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(name = "department_name",nullable = false)
//    @NotEmpty
//    @Size(min = 2, message = " department name should have at least 2 characters")
    private String departmentName;
//    @NotEmpty
//    @Size(min = 2, message = " department address should have at least 2 characters")
    private String departmentAddress;
//    @NotEmpty
//    @Size(min = 2, message = " department name should have at least 2 characters")
    private String departmentCode;
}
