package com.nte.financeapi.domain.research.api;

import com.nte.financeapi.global.common.response.Response;
import com.nte.financeapi.domain.research.dto.request.ReadResearchRequest;
import com.nte.financeapi.domain.research.dto.response.ReadResearchListResponse;
import com.nte.financeapi.domain.research.service.ResearchService;
import com.nte.financeapi.domain.research.dto.request.CreateResearchRequest;
import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financedcore.domain.Research;
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
@Tag(name = "Research", description = "Research API")
public class ResearchApiController {

    private final ResearchService researchService;

    @PostMapping
    @Operation(summary = "Create Research", description = "research 생성")
    public void createResearch(@RequestBody @Valid CreateResearchRequest request) {

        researchService.createResearch(request);
    }

    @GetMapping
    @Operation(summary = "Read all Research", description = "모든 Research들을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResearchResponseSchema.class)))
                    })
    })
    public Response<ReadResearchListResponse> readResearchAll(){
        ReadResearchListResponse response = researchService.findAll();
        return new Response<>(response);
    }
    private static class ResearchResponseSchema extends Response<List<Research>> {
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read Research by Id", description = "id로 research 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {
                            @Content(schema = @Schema(implementation = Response.class))
                    })
    })
    public Response<ReadResearchResponse> readResearchById(@PathVariable("id") Long id){
        ReadResearchResponse response = researchService.findById(id);

        return new Response<>(response);
    }

    @GetMapping("/by-tag")
    @Operation(summary = "Read Research by Tag", description = "선택된 Tag를 포함 하는 Research 조히")

    public Response<ReadResearchListResponse> readResearchAllByTag(@RequestParam List<Long> tagIdList){
        ReadResearchRequest request = new ReadResearchRequest(tagIdList);
        ReadResearchListResponse response = researchService.findAllByTag(request);

        return new Response<>(response);
    }

}
