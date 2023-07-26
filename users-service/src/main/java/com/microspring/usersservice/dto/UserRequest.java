package com.microspring.usersservice.dto;

import jakarta.validation.constraints.Email;
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
public class UserRequest {
    @NotEmpty
    @Size(min = 2, message = "user first name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "user last name should have at least 2 characters")
    private String lastName;
    @Email
    private String email;
    private String departmentId;
}
