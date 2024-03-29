package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.repository.user.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JWTUtil {

        private final UserRepository userRepository;

        private final String secret = "3hJ7mKpFqRtUwXyZaB8cVnE9x1i2g4o5N6l0sPdWfG";

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        com.star.eswasthyabackend.model.User databaseUser = userRepository.loadUserByUsername(principal.getUsername());
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 864000000);

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("userId", databaseUser.getId())
                .claim("authority", principal.getAuthorities())
                .claim("email", principal.getUsername())
                .claim("isVerified", databaseUser.getIsVerified())
                .claim("isFormFilled", databaseUser.getIsFormFilled())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateNewToken() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.star.eswasthyabackend.model.User databaseUser = userRepository.
                loadUserByUsername(userDetails.getUsername());
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 864000000);

        List<Map<String, Object>> authority = new ArrayList<>();
        Map<String, Object> authorityMap = new HashMap<>();
        authorityMap.put("authority", databaseUser.getRole());
        authority.add(authorityMap);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", databaseUser.getId())
                .claim("authority", authority)
                .claim("email", databaseUser.getEmail())
                .claim("isVerified", databaseUser.getIsVerified())
                .claim("isFormFilled", databaseUser.getIsFormFilled())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
