package com.funnycode.onlineshop.util;


import com.funnycode.onlineshop.model.TokenPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${JWT_SECRET_KEY}")
    private String secret;

    public String generateToken(TokenPayload tokenPayload, long expiredDate) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("payload", tokenPayload);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public TokenPayload getTokenPayload(String token) {
        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayload.builder().accountId((int) mapResult.get("accountId")).username((String) mapResult.get("username"))
                    .build();
        });
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);

    }

    public boolean isValid(String token, TokenPayload tokenFromAccount) {
        TokenPayload tokenPayload = getTokenPayload(token);

        return tokenPayload.getAccountId() == tokenFromAccount.getAccountId() && tokenPayload.getUsername().equals(tokenFromAccount.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getClaimsFromToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }
}
