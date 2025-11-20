package org.example.smartlifebackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtService {

    private final String SECRET_KEY = "smartlifebackendsecretkeyforsigningjwttokensmartlifebackendsecretkeyforsigningjwttokens"; // минимум 256 бит!
    private static final long ACCESS_EXPIRATION = 1000 * 60 * 60 * 24;      // 24 часа
    private static final long REFRESH_EXPIRATION = 1000L * 60 * 60 * 24 * 30; // 30 дней (или сколько хочешь)

    public String generateAccessToken(Long userId) {
        return generateToken(userId.toString(), ACCESS_EXPIRATION);
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId.toString(), REFRESH_EXPIRATION);
    }

    private String generateToken(String subject, long expirationTime) {
        return Jwts.builder()
                .setSubject(subject) // здесь теперь userId в виде строки
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS512) // лучше HS512
                .compact();
    }

    public Long extractUserId(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Long.valueOf(claims.getSubject());
        } catch (Exception e) {
            log.error("JWT parse error: {}", e.getMessage());
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}