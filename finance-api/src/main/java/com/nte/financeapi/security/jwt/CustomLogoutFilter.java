package com.nte.financeapi.security.jwt;

import com.nte.financeapi.security.service.RefreshTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public CustomLogoutFilter(JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$")) {

            filterChain.doFilter(request, response);
            return;
        }

        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {

            filterChain.doFilter(request, response);
            return;
        }


        String refreshToken = jwtUtil.getJwtToken(request.getHeader("refresh"));

        if(refreshToken == null){

            return;
        }

        // valid expiration time
        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e){

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        // valid if token is refreshToken
        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        Boolean isExist = refreshTokenService.existsByUsername(username);

        if (!isExist) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        refreshTokenService.deleteAllByUsername(username);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
