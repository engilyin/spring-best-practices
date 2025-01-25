package com.engilyin.bestpractices.rest.configs;

import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class JwtProps {

    private final int sessionTime;

    private final SecretKey key;

    public JwtProps(@Value("${jwt.secret}") String secret, @Value("${jwt.session-time}") int sessionTime) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        this.sessionTime = sessionTime;
    }
}
