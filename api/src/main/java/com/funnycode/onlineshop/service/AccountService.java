package com.funnycode.onlineshop.service;

import com.funnycode.onlineshop.dto.AccountDTOCreate;
import com.funnycode.onlineshop.dto.AccountDTOResponse;
import com.funnycode.onlineshop.dto.LoginDTORequest;
import com.funnycode.onlineshop.dto.LoginDTOResponse;

public interface AccountService {
    AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate);

    LoginDTOResponse login(LoginDTORequest loginDTORequest);
}
