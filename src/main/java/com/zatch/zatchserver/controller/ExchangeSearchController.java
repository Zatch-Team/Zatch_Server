package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.dto.GetExchangeSearchResDto;
import com.zatch.zatchserver.repository.ExchangeSearchRepository;
import com.zatch.zatchserver.service.ExchangeSearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class ExchangeSearchController {
    private final ExchangeSearchRepository searchRepository;
    private final ExchangeSearchService searchService;

    @GetMapping("")
    @ApiOperation(value = "교환할 재치", notes = "교환 재치 검색 결과 조회 API")
    public GetExchangeSearchResDto getSearch(@RequestBody GetExchangeSearchResDto getSearchResDto) {
        searchService.viewAll(getSearchResDto.getItemName1(),
                getSearchResDto.getItemName2());
        return new GetExchangeSearchResDto(getSearchResDto.getItemName1(), getSearchResDto.getItemName2());
    }
}
