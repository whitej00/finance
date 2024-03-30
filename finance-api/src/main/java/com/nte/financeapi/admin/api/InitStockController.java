package com.nte.financeapi.admin.api;

import com.nte.financedcore.common.MyDataStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InitStockController {

    private final MyDataStockService mydataStockService;

    @GetMapping("/init/stock")
    public void createStock() throws Exception{
        mydataStockService.read();
    }

}
