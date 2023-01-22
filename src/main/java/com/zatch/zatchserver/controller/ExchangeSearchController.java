package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.dto.GetExchangeSearchResDto;
import com.zatch.zatchserver.repository.ExchangeSearchRepository;
import com.zatch.zatchserver.service.ExchangeSearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class ExchangeSearchController {
    private final ExchangeSearchRepository searchRepository;
    private final ExchangeSearchService searchService;

    @GetMapping("/{itemName1}&{itemName2}")
    @ApiOperation(value = "교환할 재치", notes = "교환 재치 검색 결과 조회 API")
    public GetExchangeSearchResDto getSearch(@PathVariable("itemName1") String itemName1, @PathVariable("itemName2") String itemName2) {
        searchService.viewAll(itemName1, itemName2);
        return new GetExchangeSearchResDto(itemName1, itemName2);
    }
}
