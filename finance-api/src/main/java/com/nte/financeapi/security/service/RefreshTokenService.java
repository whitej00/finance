package com.nte.financeapi.security.service;


import com.nte.financeapi.security.dto.RefreshTokenDto;
import com.nte.financeapi.security.jwt.JWTUtil;
import com.nte.financedcore.domain.RefreshToken;
import com.nte.financedcore.domain.User;
import com.nte.financedcore.repository.RefreshTokenRepository;
import com.nte.financedcore.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshRepository;
    private final JWTUtil jwtUtil;

    @Transactional
    public void save(RefreshTokenDto refreshTokenDto){
        RefreshToken refreshToken = RefreshToken.builder()
                .username(refreshTokenDto.getUsername())
                .refreshToken(refreshTokenDto.getRefreshToken())
                .build();

        refreshRepository.save(refreshToken);
    }

    @Transactional
    public void deleteAllByUsername(String username){

        refreshRepository.deleteAllByUsername(username);
    }

    public Boolean existsByUsername(String username){

        return refreshRepository.existsByUsername(username);
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getHeader("refresh");
        if (token == null || !token.startsWith("Bearer ")) {

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        String refreshToken = jwtUtil.getJwtToken(token);

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

        Optional<RefreshToken> refreshTokenOptional = refreshRepository.findByUsername(username);

        // matched token does not exist
        if(refreshTokenOptional.isEmpty()){

            response.setStatus(HttpStatus.BAD_REQUEST.value());

            return;
        }


        String refreshTokenDb = jwtUtil.getJwtToken(refreshTokenOptional.get().getRefreshToken());

        // matched token does not exist
        if (!refreshToken.equals(refreshTokenDb)){

            response.setStatus(HttpStatus.BAD_REQUEST.value());

            return;
        }

        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){

            response.setStatus(HttpStatus.BAD_REQUEST.value());

            return;
        }

        String accessToken = jwtUtil.createJwt(jwtUtil.ACCESS_TOKEN_CATEGORY, username, role, jwtUtil.ACCESS_TOKEN_EXPIRED_MS);

        response.addHeader("access", accessToken);
    }
}
