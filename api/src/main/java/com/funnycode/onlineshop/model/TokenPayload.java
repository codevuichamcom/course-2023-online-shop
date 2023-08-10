package com.funnycode.onlineshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenPayload {
    private int accountId;
    private String username;
}
