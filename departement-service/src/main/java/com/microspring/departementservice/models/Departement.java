package com.microspring.departementservice.models;

import jakarta.persistence.*;
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
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
