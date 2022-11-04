package com.kusitms.finit.configure.security.jwt;

import com.kusitms.finit.account.entity.enumtypes.RoleType;
import com.kusitms.finit.configure.response.exception.CustomExceptionStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    private long tokenValidity = 100 * 24 * 60 * 60 * 1000L;

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(String email, RoleType role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
        if(userDetails == null) return null;
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("X-ACCESS-TOKEN");
    }

    public boolean validateToken(String jwtToken, HttpServletRequest req) {
        try{
            if(jwtToken.isEmpty()) throw new JwtException("empty jwtToken");
            Claims claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(jwtToken).getBody();
            return !claimsJws.getExpiration().before(new Date());
        } catch (JwtException e) {
            if(jwtToken.isEmpty()) {
                req.setAttribute("exception", CustomExceptionStatus.EMPTY_JWT.getMessage());
            }
            else req.setAttribute("exception", CustomExceptionStatus.INVALID_JWT.getMessage());
            return false;
        }
    }
}
