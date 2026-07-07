package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final byte[] SECRET = "12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8);

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("id", user.getId())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(Keys.hmacShaKeyFor(SECRET), SignatureAlgorithm.HS256)
                .compact();
    }  // extract login from token
    public String extractLogin(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(SECRET)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    } // Vérifie si le token est valide pour l'utilisateur donné
    public boolean isTokenValid(String token, User user) {
    String login = extractLogin(token);
    return login.equals(user.getLogin());
}


}
