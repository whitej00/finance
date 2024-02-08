package com.nte.financeapi.admin.api;

import com.nte.financedcore.domain.Stock;
import com.nte.financedcore.domain.StockTag;
import com.nte.financedcore.domain.Tag;
import com.nte.financedcore.repository.StockRepository;
import com.nte.financedcore.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class InitTagController {

    private final StockRepository stockRepository;
    private final TagRepository tagRepository;

    /**
     * tag : stock = 1 : 1
     * tag name is stock name.
     * */
    @GetMapping("/init/tagStock")
    public void createTagStock(){
        List<Stock> stockList = stockRepository.findAll();

        for(Stock stock : stockList){
            StockTag stockTag = new StockTag(stock);

            Tag tag = new Tag(stock.getName(), stockTag);

            tagRepository.save(tag);
        }
    }

    /**
     * tag : stock = 1 : N
     * tag name is sector name.
     * ex) Tech = ["kakao", "naver"..]
     * */
    @GetMapping("/init/tagSector")
    public void createTagSector(){

    }
}
