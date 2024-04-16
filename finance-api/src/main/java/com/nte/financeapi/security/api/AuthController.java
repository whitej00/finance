package com.nte.financeapi.security.api;

import com.nte.financeapi.security.dto.JoinUserRequest;
import com.nte.financeapi.security.dto.LoginDto;
import com.nte.financeapi.security.service.JoinService;
import com.nte.financeapi.security.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth based on Spring Security")
public class AuthController {

    private final JoinService joinService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Join", description = "회원 가입")
    public String joinProcess(@Valid @ModelAttribute JoinUserRequest joinUserRequest){

        joinService.joinProcess(joinUserRequest);

        return "join success!";
    }

    @PostMapping(value = "/login", consumes = "multipart/form-data")
    public String login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {

        return "login";
    }

    @GetMapping("/main")
    public String test() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller" + " " + username + " " + role;
    }

    @PatchMapping("/refresh")
    public String refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {

        refreshTokenService.refresh(request, response);

        if(response.getStatus() == HttpStatus.BAD_REQUEST.value()){
            return "refreshed failed!";
        }
        else{
            return "refreshed success!";
        }
    }

}
