package com.example.musicApp.dto.request;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDto {
    @Id
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID cannot be negative")
    private Integer id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Surname can not be empty")
    private String surName;
    @NotBlank(message = "Username can not be empty")
    private String userName;
    @NotNull(message = "Age can not be null")
    @Size(min = 16, message = "Must be at least 16 years old")
    private Integer age;
    @NotBlank(message = "CreatedAt cannot be empty")
    private LocalDateTime createdAt;
    @NotBlank(message = "CreatedBy cannot be empty")
    private String createdBy;
}
