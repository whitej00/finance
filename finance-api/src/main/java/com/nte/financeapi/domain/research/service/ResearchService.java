package com.nte.financeapi.domain.research.service;

import com.nte.financeapi.domain.research.dto.request.CreateResearchRequest;
import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financecore.domain.*;
import com.nte.financecore.repository.EvaluationStatusRepository;
import com.nte.financecore.repository.ResearchRepository;
import com.nte.financecore.repository.StockRepository;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResearchService {

    private final UserRepository userRepository;
    private final ResearchRepository researchRepository;
    private final EvaluationStatusRepository evaluationStatusRepository;
    private final StockRepository stockRepository;

    @Transactional
    public void createResearch(CreateResearchRequest request){

        User user = userRepository.findById(request.getUserId()).get();
        Stock stock = stockRepository.findById(request.getStockId()).get();

        Research research = Research.builder()
                .user(user)
                .stock(stock)
                .title(request.getTitle())
                .content(request.getContent())
                .createdDate(request.getCreatedDateTime())
                .targetRangeStart(request.getTargetRangeStart())
                .targetRangeEnd(request.getTargetRangeEnd())
                .targetPrice(request.getTargetPrice())
                .build();

        researchRepository.save(research);

        EvaluationStatus evaluationStatus = EvaluationStatus.builder()
                .user(user)
                .stock(stock)
                .research(research)
                .evaluated(Boolean.FALSE)
                .targetRangeStart(request.getTargetRangeStart())
                .targetRangeEnd(request.getTargetRangeEnd())
                .targetPrice(request.getTargetPrice())
                .marketPrice(0L)
                .build();

        evaluationStatusRepository.save(evaluationStatus);
    }

    public ReadResearchResponse readResearchById(Long id){

        Research research = researchRepository.findById(id).get();

        return ReadResearchResponse.builder()
                .username(research.getUser().getUsername())
                .title(research.getTitle())
                .content(research.getContent())
                .createdDate(research.getCreatedDate())
                .targetRangeStart(research.getTargetRangeStart())
                .targetRangeEnd(research.getTargetRangeEnd())
                .targetPrice(research.getTargetPrice())
                .build();
    }

    public List<ReadResearchResponse> readResearchList(){

        List<Research> researchList = researchRepository.findAll();

        return researchList.stream()
                .map(research -> ReadResearchResponse.builder()
                        .username(research.getUser().getUsername())
                        .title(research.getTitle())
                        .content(research.getContent())
                        .createdDate(research.getCreatedDate())
                        .targetRangeStart(research.getTargetRangeStart())
                        .targetRangeEnd(research.getTargetRangeEnd())
                        .targetPrice(research.getTargetPrice())
                        .build())
                .toList();
    }

    public List<ReadResearchResponse> readResearchListByStock(Long id){

        List<Research> researchList = researchRepository.findAllByStockId(id);

        return researchList.stream()
                .map(research -> ReadResearchResponse.builder()
                        .username(research.getUser().getUsername())
                        .title(research.getTitle())
                        .content(research.getContent())
                        .createdDate(research.getCreatedDate())
                        .targetRangeStart(research.getTargetRangeStart())
                        .targetRangeEnd(research.getTargetRangeEnd())
                        .targetPrice(research.getTargetPrice())
                        .build())
                .toList();
    }

    public List<ReadResearchResponse> readResearchListByUser(Long id){

        List<Research> researchList = researchRepository.findAllByUserId(id);

        return researchList.stream()
                .map(research -> ReadResearchResponse.builder()
                        .username(research.getUser().getUsername())
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
