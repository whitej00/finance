package com.nte.financeapi.domain.user.api;

import com.nte.financeapi.domain.comment.dto.response.ReadCommentResponse;
import com.nte.financeapi.domain.comment.service.CommentService;
import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financeapi.domain.research.service.ResearchService;
import com.nte.financeapi.domain.user.dto.response.ReadUserResponse;
import com.nte.financeapi.domain.user.service.UserService;
import com.nte.financeapi.global.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserApiController {

    private final UserService userService;
    private final ResearchService researchService;
    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Read all User", description = "모든 User 조회")
    public Response<List<ReadUserResponse>> readUserList(){

        List<ReadUserResponse> readUserResponseList = userService.readUserList();

        return new Response<>(readUserResponseList);
    }

    @GetMapping("/{id}/researches")
    @Operation(summary = "Read Research that User has", description = "User가 가진 Research 조회")
    public Response<List<ReadResearchResponse>> readResearchListByUser(@RequestParam Long id){

        List<ReadResearchResponse> readResearchResponseList = researchService.readResearchListByUser(id);

        return new Response<>(readResearchResponseList);
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Read Comment that User has", description = "User가 가진 Comment 조회")
    public Response<List<ReadCommentResponse>> readCommentListByUser(@RequestParam Long id){

        List<ReadCommentResponse> readCommentResponseList = commentService.readCommentListByUser(id);

        return new Response<>(readCommentResponseList);
    }
}
