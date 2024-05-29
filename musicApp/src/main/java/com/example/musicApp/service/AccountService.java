package com.example.musicApp.service;

import com.example.musicApp.dto.request.AccountRequestDto;
import com.example.musicApp.dto.request.LoginRequestDto;
import com.example.musicApp.dto.request.RegisterRequestDto;
import com.example.musicApp.dto.response.AccountResponseDto;
import com.example.musicApp.dto.response.AuthResponseDto;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    void deleteAccount(Integer id);

    void createAccount(AccountRequestDto accountRequestDto);

    void updatePassword(String password);

    void deleteOwnAccount(String userName, String password);

    AccountResponseDto getAccountByUserName(String email);

    ResponseEntity<String> register(RegisterRequestDto registerRequestDto);

    ResponseEntity<AuthResponseDto> login(LoginRequestDto loginRequestDto);

}
