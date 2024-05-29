package com.example.musicApp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDto {
    @NotEmpty(message = "ID cannot be empty")
    @Positive(message = "ID cannot be negative")
    private Integer id;
    @NotBlank(message = "e-mail cannot be empty")
    @Email
    private String eMail;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 8, max = 20, message = "Minimum size must be 8 and maximum size must be 20")
    private String password;
    @NotNull(message = "age cannot be empty")
    @Size(min = 16, message = "Must be at least 16 years old")
    private Integer age;
    @NotBlank(message = "username cannot be empty")
    private String userName;

}
