package com.nte.financeapi.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateCommentRequest {

    private Long userId;
    private Long researchId;
    private Long parentId;
    private String content;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
