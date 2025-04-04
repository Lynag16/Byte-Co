package com.glop.authentification.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "906864c0e88d0b3c459483c04d8b7845d701bc572e99f89d2e13fd96117fb8ab86562feef1d54b838a337f63aad287b171fd95c9c8cdaeda45e1ee509f57b23024733924884f4aaa104b4d59008e3ad2da6f5b0b9400e215afbcedeef96cdb2a74cd1e2cee5c86cec84c285faca1b6d671744341691f2a41e723764976a896c03ee24ae3d8dc3097edcad498cf245eccbf1e397e5cddc505ed41ca93f31ffca16d2959198fa35e493a5fd5d40d47b89bede8f79b875609bb2e393d2fd9434d7d800c58a93a39b3c706cfa826c4014dfea5d55b18a9d86c430664b34f5165a5cbab001bb04231f7a7341c7e2a0efd0a19c50131ac68876887453d5016c6648819";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
}
