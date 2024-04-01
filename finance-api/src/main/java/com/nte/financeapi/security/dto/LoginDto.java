package com.nte.financeapi.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;

    @Builder
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

