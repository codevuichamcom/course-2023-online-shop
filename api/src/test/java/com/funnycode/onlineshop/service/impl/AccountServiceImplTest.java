package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.dto.AccountDTOCreate;
import com.funnycode.onlineshop.dto.AccountDTOResponse;
import com.funnycode.onlineshop.entity.Account;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.repository.AccountRepository;
import com.funnycode.onlineshop.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class AccountServiceImplTest {
    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Test
    void createAccount_with_username_is_null() {
        // GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username(null)
                .password("123")
                .email("email")
                .build();

        // WHEN
        // THEN
        OnlineShopException actualThrow = assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        assertEquals("Username is not null", actualThrow.getError().getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_email_is_null() {
        // GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("username")
                .password("123")
                .email(null)
                .build();

        // WHEN
        // THEN
        OnlineShopException actualThrow = assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        assertEquals("Email is not null", actualThrow.getError().getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_username_is_existed() {
        // GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("existedUsername")
                .password("123")
                .email("email@gmail.com")
                .build();

        // WHEN
        when(accountRepository.existsByUsername("existedUsername")).thenReturn(true);
        // THEN
        OnlineShopException actualThrow = assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        assertEquals("Username is existed", actualThrow.getError().getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void createAccount_with_email_is_existed() {
        // GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("username")
                .password("123")
                .email("existedMail@gmail.com")
                .build();

        // WHEN
        when(accountRepository.existsByUsername("username")).thenReturn(false);
        when(accountRepository.existsByEmail("existedMail@gmail.com")).thenReturn(true);
        // THEN
        OnlineShopException actualThrow = assertThrows(OnlineShopException.class, () -> accountService.createAccount(accountDTOCreate));
        assertEquals(HttpStatus.BAD_REQUEST, actualThrow.getStatus());
        assertEquals("Email is existed", actualThrow.getError().getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void createAccount_success() {
        // GIVEN
        AccountDTOCreate accountDTOCreate = AccountDTOCreate.builder()
                .username("username")
                .password("123")
                .email("email@gmail.com")
                .build();

        Account savedAccount = Account.builder()
                .id(1)
                .username("username")
                .password("123")
                .email("email@gmail.com")
                .build();
        // WHEN
        when(accountRepository.existsByUsername("username")).thenReturn(false);
        when(accountRepository.existsByEmail("email@gmail.com")).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);
        // THEN
        AccountDTOResponse actualAccountDTOResponse = accountService.createAccount(accountDTOCreate);
        verify(accountRepository, times(1)).save(any(Account.class));
        assertEquals(1,actualAccountDTOResponse.getId());
        assertEquals("username",actualAccountDTOResponse.getUsername());
        assertEquals("email@gmail.com",actualAccountDTOResponse.getEmail());
    }
}