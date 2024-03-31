package com.nte.financeapi.security.api;

import com.nte.financeapi.security.dto.JoinDto;
import com.nte.financeapi.security.service.JoinService;
import com.nte.financeapi.security.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth based on Spring Security")
public class AuthController {

    private final JoinService joinService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/auth/join")
    @Operation(summary = "Join", description = "회원 가입")
    public String joinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);

        return "join success!";
    }

    @PatchMapping("/auth/refresh")
    public String reissue(HttpServletRequest request, HttpServletResponse response) throws IOException {

        refreshTokenService.refresh(request, response);

        return "refreshed success!";
    }

}
