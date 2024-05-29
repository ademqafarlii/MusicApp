package com.example.musicApp.mapper;

import com.example.musicApp.dto.request.AccountRequestDto;
import com.example.musicApp.dto.request.RegisterRequestDto;
import com.example.musicApp.dto.response.AccountResponseDto;
import com.example.musicApp.exception.AccountNotFoundException;
import com.example.musicApp.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public Account toAccountModel(AccountRequestDto accountRequestDto) {

        if (accountRequestDto == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return Account.builder()
                .age(accountRequestDto.getAge())
                .eMail(accountRequestDto.getEMail())
                .userName(accountRequestDto.getUserName())
                .password(accountRequestDto.getPassword())
                .id(accountRequestDto.getId())
                .build();
    }

    public AccountResponseDto toAccountDto(Account account) {
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return AccountResponseDto.builder()
                .age(account.getAge())
                .eMail(account.getEMail())
                .userName(account.getUserName())
                .password(account.getPassword())
                .build();
    }
}
