package org.example.dictionary.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public class JwtUtils
{
    @Value("${myjwttoken.app.jwtSecret}")
    private String jwtSecret;
    @Value("${myjwttoken.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserName(UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .toList();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuer("myjwttoken")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}" + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Signature validation failed" + e);
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
