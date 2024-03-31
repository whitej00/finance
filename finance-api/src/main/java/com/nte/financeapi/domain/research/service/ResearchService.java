package com.nte.financeapi.domain.research.service;

import com.nte.financeapi.domain.research.dto.request.CreateResearchRequest;
import com.nte.financeapi.domain.research.dto.request.ReadResearchRequest;
import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financedcore.domain.Research;
import com.nte.financedcore.domain.ResearchTag;
import com.nte.financedcore.domain.Tag;
import com.nte.financedcore.repository.ResearchRepository;
import com.nte.financedcore.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResearchService {

    private final TagRepository tagRepository;
    private final ResearchRepository researchRepository;

    @Transactional
    public void createResearch(CreateResearchRequest request){
        List<Tag> tagList = tagRepository.findByIdIn(request.getResearchTagIdList());
        List<ResearchTag> researchTagList = tagList.stream()
                .map(tag -> ResearchTag.builder()
                        .tag(tag)
                        .build())
                .toList();

        Research research = Research.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdDate(request.getCreatedDate())
                .targetRangeStart(request.getTargetRangeStart())
                .targetRangeEnd(request.getTargetRangeEnd())
                .targetPrice(request.getTargetPrice())
                .researchTagList(researchTagList)
                .build();

        researchRepository.save(research);
    }

    public ReadResearchResponse findById(Long id){
        Research research = researchRepository.findById(id).get();

        return ReadResearchResponse.builder()
                .id(research.getId())
                .title(research.getTitle())
                .content(research.getContent())
                .createdDate(research.getCreatedDate())
                .targetRangeStart(research.getTargetRangeStart())
                .targetRangeEnd(research.getTargetRangeEnd())
                .targetPrice(research.getTargetPrice())
                .build();
    }

    public List<ReadResearchResponse> findAll(){
        List<Research> researchList = researchRepository.findAll();

        return researchList.stream()
                .map(research -> ReadResearchResponse.builder()
                        .id(research.getId())
                        .title(research.getTitle())
                        .content(research.getContent())
                        .createdDate(research.getCreatedDate())
                        .targetRangeStart(research.getTargetRangeStart())
                        .targetRangeEnd(research.getTargetRangeEnd())
                        .targetPrice(research.getTargetPrice())
                        .build())
                .toList();
    }

    public List<ReadResearchResponse> findAllByTag(ReadResearchRequest request){
        List<Tag> tagList = tagRepository.findByIdIn(request.getTagIdList());

        return tagList.stream()
                .map(Tag::getResearchTagList)
                .flatMap(Collection::stream)
                .map(ResearchTag::getResearch)
                .distinct()
                .map(research -> ReadResearchResponse.builder()
                        .id(research.getId())
                        .title(research.getTitle())
                        .content(research.getContent())
                        .createdDate(research.getCreatedDate())
                        .targetRangeStart(research.getTargetRangeStart())
                        .targetRangeEnd(research.getTargetRangeEnd())
                        .targetPrice(research.getTargetPrice())
                        .build())
                .toList();
    }
}
