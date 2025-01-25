package com.engilyin.bestpractices.rest.services.system;

import com.engilyin.bestpractices.rest.Const;
import com.engilyin.bestpractices.rest.configs.JwtProps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final JwtProps jwtProps;

    public String toToken(long userId, String role) {

        Claims claims = Jwts.claims().setSubject(userId + "");
        claims.put(Const.AUTHORITIES_KEY, role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://engilyin.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(jwtProps.getSessionTime(), ChronoUnit.SECONDS)))
                .signWith(jwtProps.getKey())
                .compact();
    }

    public Optional<Long> getSubFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return Optional.of(Long.parseLong(claims.getSubject()));
        } catch (Exception e) {
            log.error("Unable to decrypt the auth token: {}", e.toString());
            return Optional.empty();
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProps.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
