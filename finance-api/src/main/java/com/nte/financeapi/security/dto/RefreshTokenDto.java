package com.nte.financeapi.security.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class RefreshTokenDto {

    private String username;
    private String refreshToken;

    @Builder
    public RefreshTokenDto(String username, String refreshToken) {
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
