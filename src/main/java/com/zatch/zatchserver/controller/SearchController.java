package com.zatch.zatchserver.controller;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.repository.SearchRepository;
import com.zatch.zatchserver.service.SearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    //private final SearchRepository searchRepository;
    private final SearchService searchService;

    @GetMapping("/{keyword}")
    @ApiOperation(value = "아이템 이름", notes = "아이템 이름으로 재치 조회 API")
    public GetSearchResDto searchKeyword(@PathVariable("keyword") String keyword) {
        //String keyword= getSearchResDto.getKeyword();
        searchService.findByItemName(keyword);
        return new GetSearchResDto(keyword);
    }

}
