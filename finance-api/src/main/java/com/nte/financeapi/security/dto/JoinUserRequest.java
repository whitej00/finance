package com.nte.financeapi.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinUserRequest {

    private String username;
    private String password;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @Builder
    public JoinUserRequest(String username, String password, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.username = username;
        this.password = password;

        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }
}
