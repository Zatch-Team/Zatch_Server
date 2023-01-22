package com.zatch.zatchserver.controller;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.repository.SearchRepository;
import com.zatch.zatchserver.service.SearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class SearchController {

    private final SearchRepository searchRepository;
    private final SearchService searchService;

    @GetMapping("/search")
    @ApiOperation(value = "keyword", notes = "아이템 이름으로 재치 조회 API")
    public GetSearchResDto searchKeyword(@RequestBody GetSearchResDto getSearchResDto) {
        String keyword= getSearchResDto.getKeyword();

        searchService.findByItemName(keyword);
        return new GetSearchResDto(keyword);
    }

}
