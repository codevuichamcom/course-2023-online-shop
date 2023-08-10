package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.LoginDTORequest;
import com.funnycode.onlineshop.dto.LoginDTOResponse;
import com.funnycode.onlineshop.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static com.funnycode.onlineshop.util.Constant.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(API_VERSION)
@CrossOrigin
public class AuthenticationController {

    AccountService accountService;

    @PostMapping("/login")
    public LoginDTOResponse login(@RequestBody LoginDTORequest loginDTORequest) {
        return accountService.login(loginDTORequest);
    }
}
