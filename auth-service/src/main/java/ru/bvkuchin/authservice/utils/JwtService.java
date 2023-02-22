package ru.bvkuchin.authservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value(value = "${jwt.lifetime}")
    private String EXPIRATION_TIME_STR;// = 1000 * 60 * 10;

    @Value(value = "${jwt.secret}")
    private String SECRET;// = "lsdkcniuenfxie7f734pr734yxnpehfh";

    public String generateToken(UserDetails userDetails) {
        Long EXPIRATION_TIME = Long.parseLong(EXPIRATION_TIME_STR);
        String userName = userDetails.getUsername();

        List<String> authorities = userDetails
                .getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>(Map.of("authorities", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUserName(String tokenValue) {
        return parse(tokenValue).getSubject();
    }

    public List<? extends GrantedAuthority> getAuthorities(String tokenValue) {
        List<String> authorities = (List<String>) parse(tokenValue).get("authorities");
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    private Claims parse(String tokenValue) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(tokenValue)
                .getBody();
    }
}

