package com.example.musicApp.service.impl;

import com.example.musicApp.dto.request.AccountRequestDto;
import com.example.musicApp.dto.request.LoginRequestDto;
import com.example.musicApp.dto.request.RegisterRequestDto;
import com.example.musicApp.dto.response.AccountResponseDto;
import com.example.musicApp.dto.response.AuthResponseDto;
import com.example.musicApp.exception.AccountNotFoundException;
import com.example.musicApp.exception.IncorrectInformationException;
import com.example.musicApp.exception.PasswordCannotBeSameException;
import com.example.musicApp.mapper.AccountMapper;
import com.example.musicApp.model.Account;
import com.example.musicApp.model.RoleModel;
import com.example.musicApp.repository.AccountRepository;
import com.example.musicApp.repository.RoleRepository;
import com.example.musicApp.security.JWTGenerator;
import com.example.musicApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JWTGenerator jwtGenerator;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository, JWTGenerator jwtGenerator) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void createAccount(AccountRequestDto accountRequestDto) {
        accountRepository.save(accountMapper.toAccountModel(accountRequestDto));
    }

    @Override
    public void updatePassword(String password) {

        Account account = accountRepository.findByPassword(password);
        if (account != null && account.getPassword().equals(password)) {
            throw new PasswordCannotBeSameException("Password cannot be the same as the previous one");
        } else if (account != null) {
            account.setPassword(password);
            accountRepository.save(account);
        }
    }

    @Override
    public void deleteOwnAccount(String userName, String password) {
        Account account = accountRepository.findByPassword(password);
        Optional<Account> account1 = accountRepository.findByUserName(userName);
        if (account1.get().getUserName().equals(userName) && account.getPassword().equals(password)) {
            accountRepository.deleteAccountByUserNameAndPassword(userName, password);
        } else {
            throw new IncorrectInformationException("Username or password is invalid");
        }
    }

    @Override
    public AccountResponseDto getAccountByUserName(String userName) {
        return accountRepository
                .findByUserName(userName)
                .stream()
                .map(accountMapper::toAccountDto)
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }


    @Override
    public ResponseEntity<String> register(RegisterRequestDto registerRequestDto) {
        if (accountRepository.existsByUserName(registerRequestDto.getUserName())) {
            return new ResponseEntity<>("userName is taken", HttpStatus.BAD_REQUEST);
        }
        Account account = new Account();
        account.setUserName(registerRequestDto.getUserName());
        account.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        RoleModel roleModel = roleRepository.findByName("USER").get();
        account.setRoles(Collections.singletonList(roleModel));

        accountRepository.save(account);

        return new ResponseEntity<>("User registered success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthResponseDto> login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginRequestDto.getUserName(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =  jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }


}
