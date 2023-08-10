package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.entity.Account;
import com.funnycode.onlineshop.dto.LoginDTORequest;
import com.funnycode.onlineshop.dto.LoginDTOResponse;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.AccountMapper;
import com.funnycode.onlineshop.dto.AccountDTOCreate;
import com.funnycode.onlineshop.dto.AccountDTOResponse;
import com.funnycode.onlineshop.repository.AccountRepository;
import com.funnycode.onlineshop.service.AccountService;
import com.funnycode.onlineshop.util.JwtTokenUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenUtil jwtTokenUtil;

    @Override
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate) {
        if (accountDTOCreate.getUsername() == null) {
            throw OnlineShopException.badRequestException("Username is not null");
        }
        if(accountDTOCreate.getEmail() == null){
            throw OnlineShopException.badRequestException("Email is not null");
        }
        if(accountRepository.existsByUsername(accountDTOCreate.getUsername())){
            throw OnlineShopException.badRequestException("Username is existed");
        }
        if(accountRepository.existsByEmail(accountDTOCreate.getEmail())){
            throw OnlineShopException.badRequestException("Email is existed");
        }
        accountDTOCreate.setPassword(passwordEncoder.encode(accountDTOCreate.getPassword()));
        Account account = accountRepository.save(AccountMapper.toAccount(accountDTOCreate));
        return AccountMapper.toAccountDTOResponse(account);
    }

    @Override
    public LoginDTOResponse login(LoginDTORequest loginDTORequest) {
        Optional<Account> accountOptional = accountRepository.findByUsername(loginDTORequest.getUsername());
        boolean isAuthentication = false;
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (passwordEncoder.matches(loginDTORequest.getPassword(), account.getPassword())) {
                isAuthentication = true;
            }
        }
        if (!isAuthentication) {
            throw OnlineShopException.badRequestException("Username or password incorrect");
        }

        Account account = accountOptional.get();
        final int ONE_DAY_MILLISECONDS = 24 * 60 * 60;
        String accessToken = jwtTokenUtil.generateToken(AccountMapper.toTokenPayload(account), ONE_DAY_MILLISECONDS);
        return LoginDTOResponse.builder()
                .account(AccountMapper.toAccountDTOResponse(account))
                .accessToken(accessToken)
                .build();
    }
}
