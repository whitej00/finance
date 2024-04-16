package com.nte.financeapi.domain.comment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ReadCommentResponse {

    private Long id;
    private Long userId;
    private String username;
    private String content;
    private Boolean updated;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private List<ReadCommentResponse> childList = new ArrayList<>();

    @Builder
    public ReadCommentResponse(Long id, Long userId, String username, String content, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.updated = Boolean.FALSE;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }
}
