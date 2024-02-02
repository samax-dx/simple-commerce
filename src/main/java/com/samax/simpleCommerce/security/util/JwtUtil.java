package com.samax.simpleCommerce.security.util;

import com.samax.simpleCommerce.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private Long accessTokenExpiry;


    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("roles", user.getRoles().stream().map(Enum::name).collect(Collectors.joining(",")));

        Date tokenExpiry = new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(accessTokenExpiry));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenExpiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String tokenHeader = "Authorization";
        String bearerToken = request.getHeader(tokenHeader);

        if (bearerToken == null) return null;

        String tokenPrefix = "Bearer ";
        if (!bearerToken.startsWith(tokenPrefix)) return null;

        return bearerToken.substring(tokenPrefix.length());
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token == null) return null;
            return parseJwtClaims(token);
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    private Claims parseJwtClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String getSubject(Claims claims) {
        return claims.getSubject();
    }

    public List<String> getRoles(Claims claims) {
        return List.of(((String) claims.get("roles")).split(","));
    }

}
