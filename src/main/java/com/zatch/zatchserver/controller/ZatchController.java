package com.zatch.zatchserver.controller;


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
}
