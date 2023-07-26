package com.microspring.usersservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity()
public class Users {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "users_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "user first name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "user last name should have at least 2 characters")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;
    private String departmentId;
}
