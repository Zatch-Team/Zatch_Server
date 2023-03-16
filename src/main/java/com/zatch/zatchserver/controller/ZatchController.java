package com.zatch.zatchserver.controller;


import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;
import com.zatch.zatchserver.dto.PostZatchReq;
import com.zatch.zatchserver.service.ZatchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zatch")
public class ZatchController {

    private final ZatchService postService;

    //메인페이지 조회 리스트
    @GetMapping("")
    public List<Zatch> getPost(){
        return postService.getPostList();

    }

    @PostMapping("")
    @ApiOperation(value = "재치 등록", notes = "재치등록 API")
    public void makeNewZatch(@RequestBody PostZatchReq postZatchReq) {
        Zatch newPost = new Zatch(
                postZatchReq.getUserId(),
                postZatchReq.getCategoryId(),
                postZatchReq.getIsFree(),
                postZatchReq.getItemName(),
                postZatchReq.getContent(),
                postZatchReq.getQuantity(),
                postZatchReq.getPurchaseDate(),
                postZatchReq.getExpirationDate(),
                postZatchReq.getIsOpened(),
                postZatchReq.getAnyZatch()
        );

        postService.register(newPost);
    }

    //내 재치 검색어 띄우기
    @GetMapping("/{userId}/search")
    @ApiOperation(value = "교환할 수 있는 재치", notes = "검색 시, 내 재치 조회 API")
    public List<ViewMyZatch> getMyZatch(@PathVariable("userId") Long userId){
        return postService.getZatchName(userId);
    }

    //교환할 재치 검색 결과
    @GetMapping("/{userId}/search/{itemName1}&{itemName2}")
    @ApiOperation(value = "교환할 재치", notes = "교환 재치 검색 결과 조회 API")
    public List<ExchangeSearch> getSearch(@PathVariable("itemName1") String itemName1, @PathVariable("itemName2") String itemName2) {
        return postService.viewAll(itemName1, itemName2);
    }
}
