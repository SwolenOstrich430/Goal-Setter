package com.example.goalsetter.security;

import javax.servlet.http.HttpServletRequest;

import com.example.goalsetter.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final int HOUR = 3600 * 1000;
    private static final int TIME_TO_EXPIRATION = 3;
    @Value("${jwt.secret}")
    private String secret;


    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            return "";
        }
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch(JwtException error) {
            error.printStackTrace();
            return null;
        }
    }

    public boolean tokenIsExpired(String token) {
        Claims body = getClaims(token);
        Date expiration = body.getExpiration();

        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Date expirationDate = getExpiration();

        Claims claims  = Jwts.claims()
                .setExpiration(expirationDate)
                .setSubject(user.getUsername());
        claims.put("email", String.valueOf(user.getEmail()));
        claims.put("id", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Date getExpiration() {
        Date now = new Date();

        return new Date(now.getTime() + TIME_TO_EXPIRATION * HOUR);
    }
}
