package com.funnycode.onlineshop.security;


import com.funnycode.onlineshop.entity.Account;
import com.funnycode.onlineshop.model.TokenPayload;
import com.funnycode.onlineshop.mapper.AccountMapper;
import com.funnycode.onlineshop.repository.AccountRepository;
import com.funnycode.onlineshop.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayload tokenPayLoad = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.split(" ")[1];

            try {
                tokenPayLoad = jwtTokenUtil.getTokenPayload(token);
            } catch (IllegalArgumentException ex) {
                System.out.println("Unable to get JWT");
            } catch (ExpiredJwtException ex) {
                System.out.println("Token has expired");
            }
        } else {
            System.out.println("JWT Token does not start with 'Bearer '");
        }

        if (tokenPayLoad != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Account> accountOptional = accountRepository.findById(tokenPayLoad.getAccountId());

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();

                // check token co hop le hay ko
                if (jwtTokenUtil.isValid(token, AccountMapper.toTokenPayload(account))) {

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getUsername(),
                            account.getPassword(), authorities);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);

    }

}
