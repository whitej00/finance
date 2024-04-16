package com.nte.financeapi.domain.comment.api;

import com.nte.financeapi.domain.comment.dto.request.CreateCommentRequest;
import com.nte.financeapi.domain.comment.dto.request.UpdateCommentRequest;
import com.nte.financeapi.domain.comment.dto.response.ReadCommentResponse;
import com.nte.financeapi.domain.comment.service.CommentService;
import com.nte.financeapi.global.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment API")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Create Comment", description = "Comment 생성")
    public void createComment(@Valid @RequestBody CreateCommentRequest request){

        commentService.createComment(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read all Comment By id", description = "research별 모든 Comment 조회")
    public Response<List<ReadCommentResponse>> readCommentListByResearch(@RequestParam Long id){
        List<ReadCommentResponse> readCommentResponseList = commentService.findAllByResearch(id);

        return new Response<>(readCommentResponseList);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Comment By id", description = "Comment 삭제")
    public void deleteCommentById(@RequestParam Long id){

        commentService.deleteCommentById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Comment By id", description = "Comment 수정")
    public void updateComment(@Valid @RequestBody UpdateCommentRequest request){

        commentService.update(request);
    }
}
