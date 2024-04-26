package com.nte.chunk;

import com.nte.financecore.domain.EvaluationStatus;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.EvaluationStatusRepository;
import com.nte.financecore.repository.StockPriceRepository;
import com.nte.financecore.repository.StockRepository;
import com.nte.dto.EvaluationStatusDto;
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
            User user = evaluationStatus.getUser();


            return EvaluationStatusDto.builder().

                    build();
        }
        else {

            return null;
        }
    }
}



