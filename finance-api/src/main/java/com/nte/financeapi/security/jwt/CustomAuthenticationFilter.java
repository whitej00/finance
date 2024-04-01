package com.nte.financeapi.security.jwt;

import com.nte.financeapi.security.dto.CustomUserDetails;
import com.nte.financeapi.security.dto.RefreshTokenDto;
import com.nte.financeapi.security.service.RefreshTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    // case login successful (create jwt)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String accessToken = jwtUtil.createJwt(jwtUtil.ACCESS_TOKEN_CATEGORY, username, role, jwtUtil.ACCESS_TOKEN_EXPIRED_MS);
        String refreshToken = jwtUtil.createJwt(jwtUtil.REFRESH_TOKEN_CATEGORY, username, role, jwtUtil.REFRESH_TOKEN_EXPIRED_MS);

        RefreshTokenDto dto = RefreshTokenDto.builder()
                .username(username)
                .refreshToken(refreshToken)
                .build();

        refreshTokenService.deleteAllByUsername(username);
        refreshTokenService.save(dto);

        response.addHeader("Refresh", refreshToken);
        response.addHeader("Access", accessToken);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    // case login unsuccessful
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }
}
