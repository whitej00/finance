package com.nte.stockPrice;

import com.nte.financedcore.domain.EvaluationStatus;
import com.nte.financedcore.domain.Research;
import com.nte.financedcore.domain.Stock;
import com.nte.financedcore.domain.User;
import com.nte.financedcore.repository.EvaluationStatusRepository;
import com.nte.financedcore.repository.StockPriceRepository;
import com.nte.financedcore.repository.StockRepository;
import com.nte.stockPrice.dto.EvaluationStatusDto;
import com.nte.stockPrice.dto.StockDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRatingReader implements ItemReader<EvaluationStatusDto> {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;
    private final EvaluationStatusRepository evaluationStatusRepository;
    private List<EvaluationStatus> evaluationStatusList;
    private Iterator<EvaluationStatus> iterator;

    @PostConstruct
    private void initializeStocks() {

        this.evaluationStatusList = evaluationStatusRepository.findAll();
        this.iterator = evaluationStatusList.iterator();
    }

    /**
     * TODO
     * read evaluationStatus, User, StockPrice
     * */
    @Override
    public EvaluationStatusDto read() throws Exception {

        if (iterator.hasNext()) {
            EvaluationStatus evaluationStatus = iterator.next();
            Research research = evaluationStatus.getResearch();
            User user = research.getUser();
            Stock stock = research.getResearchTagList().get(0).getTag().getStockTagList().get(0).getStock();


            return EvaluationStatusDto.builder().build();
        }
        else {

            return null;
        }
    }
}



