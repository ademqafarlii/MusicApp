package com.example.musicApp.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String userName;
    @Size(min = 6, max = 30, message = "Minimum 6, maximum 30 letter")
    private String password;


}
