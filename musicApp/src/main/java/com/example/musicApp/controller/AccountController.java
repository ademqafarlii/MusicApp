package com.example.musicApp.controller;

import com.example.musicApp.dto.request.AccountRequestDto;
import com.example.musicApp.dto.request.LoginRequestDto;
import com.example.musicApp.dto.request.RegisterRequestDto;
import com.example.musicApp.dto.response.AccountResponseDto;
import com.example.musicApp.dto.response.AuthResponseDto;
import com.example.musicApp.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/a1/account")
public class AccountController {

    private final AccountServiceImpl accountService;



    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteAccount(@PathVariable Integer id) {
        log.info("request accepted");
        accountService.deleteAccount(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete-account")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public void deleteOwnAccount
            (@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        log.info("request accepted");
        accountService.deleteOwnAccount(userName, password);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-account-by-username/{userName}")
    public AccountResponseDto getAccountByUserName(@PathVariable String userName) {
        return accountService.getAccountByUserName(userName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/create-account")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void createAccount(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        log.info("account created");
        accountService.createAccount(accountRequestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/update-password")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public void updatePassword(@RequestBody String password) {
        log.info("password successfully updated");
        accountService.updatePassword(password);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return accountService.register(registerRequestDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return accountService.login(loginRequestDto);
    }


}
