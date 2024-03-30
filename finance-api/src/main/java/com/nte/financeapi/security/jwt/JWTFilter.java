package com.nte.financeapi.security.jwt;

import com.nte.financeapi.security.dto.CustomUserDetails;
import com.nte.financedcore.domain.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Bearer + accessToken
        String token = request.getHeader("Access");

        if (token == null || !token.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;
        }

        // accessToken
        String accessToken = jwtUtil.getJwtToken(token);

        // valid expiration time
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e){

            response.getWriter().print("access token is expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // valid if token is accessToken
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            response.getWriter().print("this token is not accessToken");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        User user = User.builder()
                .username(username)
                .password("tmp")
                .role(role)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
