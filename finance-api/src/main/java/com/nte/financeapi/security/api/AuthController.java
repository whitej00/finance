package com.nte.financeapi.security.api;

import com.nte.financeapi.domain.research.dto.request.CreateResearchRequest;
import com.nte.financeapi.security.dto.CustomUserDetails;
import com.nte.financeapi.security.dto.JoinDto;
import com.nte.financeapi.security.dto.LoginDto;
import com.nte.financeapi.security.dto.RefreshTokenDto;
import com.nte.financeapi.security.jwt.CustomAuthenticationFilter;
import com.nte.financeapi.security.jwt.JWTUtil;
import com.nte.financeapi.security.service.JoinService;
import com.nte.financeapi.security.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth based on Spring Security")
public class AuthController {

    private final JoinService joinService;
    private final RefreshTokenService refreshTokenService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/join")
    @Operation(summary = "Join", description = "회원 가입")
    public String joinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);

        return "join success!";
    }
    @PostMapping(value = "/auth/login", consumes = "multipart/form-data")
    public String login(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("loginDto = " + loginDto);
        return "login";
    }

    @GetMapping("/auth/main")
    public String test() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();


        return "Main Controller" + " " + username + " " + role;
    }

    @PatchMapping("/auth/refresh")
    public String reissue(HttpServletRequest request, HttpServletResponse response) throws IOException {

        refreshTokenService.refresh(request, response);

        return "refreshed success!";
    }

}
