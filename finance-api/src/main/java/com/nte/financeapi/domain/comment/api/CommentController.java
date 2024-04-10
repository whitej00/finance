package com.nte.financeapi.domain.comment.api;

import com.nte.financeapi.domain.comment.dto.request.CreateCommentRequest;
import com.nte.financeapi.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Create Comment", description = "Comment 생성")
    public void createComment(@Valid @RequestBody CreateCommentRequest request){

        commentService.createComment(request);
    }
}
