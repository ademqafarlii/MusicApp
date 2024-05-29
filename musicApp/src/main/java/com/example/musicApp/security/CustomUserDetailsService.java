package com.example.musicApp.security;

import com.example.musicApp.model.Account;
import com.example.musicApp.model.RoleModel;
import com.example.musicApp.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository
                .findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
        return new User(account.getUserName(),account.getPassword(),mapRolesToAuthorities(account.getRoles()));

    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleModel> roleModels){
        return roleModels.stream().map(roleModel -> new SimpleGrantedAuthority(roleModel.getName())).collect(Collectors.toList());
    }

}
