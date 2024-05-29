package com.example.musicApp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String userName;
    @Size(min = 6, max = 30, message = "Minimum 6, maximum 30 letter")
    private String password;
}
