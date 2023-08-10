package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.entity.Account;
import com.funnycode.onlineshop.model.TokenPayload;
import com.funnycode.onlineshop.dto.AccountDTOCreate;
import com.funnycode.onlineshop.dto.AccountDTOResponse;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate) {
        return Account.builder()
                .username(accountDTOCreate.getUsername())
                .email(accountDTOCreate.getEmail())
                .password(accountDTOCreate.getPassword())
                .avatar(accountDTOCreate.getAvatar())
                .build();
    }

    public static AccountDTOResponse toAccountDTOResponse(Account account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .avatar(account.getAvatar())
                .build();
    }

    public static TokenPayload toTokenPayload(Account account) {
        return TokenPayload.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .build();
    }
}
