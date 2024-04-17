package com.nte.financeapi.domain.research.api;

import com.nte.financeapi.domain.comment.dto.response.ReadCommentResponse;
import com.nte.financeapi.domain.comment.service.CommentService;
import com.nte.financeapi.global.common.response.Response;
import com.nte.financeapi.domain.research.service.ResearchService;
import com.nte.financeapi.domain.research.dto.request.CreateResearchRequest;
import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financecore.domain.Research;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**r
 * There is no "update" or "delete" because of the rating system
 * When the user table is created, it will be modified
 * */
@RestController
@RequestMapping("/api/researches")
@RequiredArgsConstructor
@Tag(name = "Research", description = "Research API (because of business logic, delete and update do not exist.")
public class ResearchApiController {

    private final ResearchService researchService;
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Create Research", description = "research 생성")
    public void createResearch(@Valid @RequestBody CreateResearchRequest request) {

        researchService.createResearch(request);
    }

    @GetMapping
    @Operation(summary = "Read all Research", description = "모든 Research 조회")
    public Response<List<ReadResearchResponse>> readResearchList(){
        List<ReadResearchResponse> readResearchResponseList = researchService.readResearchList();
        return new Response<>(readResearchResponseList);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Read Research by Id", description = "id로 Research 조회")
    public Response<ReadResearchResponse> readResearchById(@PathVariable("id") Long id){
        ReadResearchResponse readResearchResponse = researchService.readResearchById(id);

        return new Response<>(readResearchResponse);
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Read Comment that Research has", description = "Research가 가진 Comment 조회")
    public Response<List<ReadCommentResponse>> readCommentListByResearch(@RequestParam Long id){
        List<ReadCommentResponse> readCommentResponseList = commentService.readCommentListByResearch(id);

        return new Response<>(readCommentResponseList);
    }
}
