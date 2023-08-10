package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.AccountDTOCreate;
import com.funnycode.onlineshop.dto.AccountDTOResponse;
import com.funnycode.onlineshop.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static com.funnycode.onlineshop.util.Constant.*;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(API_VERSION + "/accounts")
@CrossOrigin
public class AccountController {
    AccountService accountService;

    @PostMapping
    public AccountDTOResponse createAccount(@RequestBody AccountDTOCreate accountDTOCreate) {
        return accountService.createAccount(accountDTOCreate);
    }
}
