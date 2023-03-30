package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.dto.NewZatchParam;
import com.zatch.zatchserver.dto.UserInfo;
import com.zatch.zatchserver.dto.controller.GetPopularZatchItemRes;
import com.zatch.zatchserver.dto.controller.GetUserRes;
import com.zatch.zatchserver.dto.controller.PostZatchLikeRes;
import com.zatch.zatchserver.dto.controller.PostZatchReq;
import com.zatch.zatchserver.service.ZatchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zatchs")
public class ZatchController {
    private final ZatchService zatchService;

    @PostMapping("")
    public void postZatch(HttpServletRequest request, @RequestBody PostZatchReq postZatchReq) {
        NewZatchParam newZatchParam = NewZatchParam.builder()
                .userId(postZatchReq.getUserId())
                .categoryId(postZatchReq.getCategoryId())
                .isFree(postZatchReq.getIsFree())
                .itemName(postZatchReq.getItemName())
                .content(postZatchReq.getContent())
                .quantity(postZatchReq.getQuantity())
                .purchaseDate(postZatchReq.getPurchaseDate())
                .expirationDate(postZatchReq.getExpirationDate())
                .isOpened(postZatchReq.getIsOpened())
                .allowAnyZatch(postZatchReq.getAllowAnyZatch())
                .build();

        zatchService.register(newZatchParam);
    }

    @PostMapping("/{zatchId}/likes")
    public PostZatchLikeRes postZatchlike(HttpServletRequest request, @PathVariable("zatchId") Long zatchId) {
        Long userId = (Long) request.getAttribute("userId");

        Integer likeCount = zatchService.makeZatchLike(userId, zatchId);

        return new PostZatchLikeRes(zatchId, likeCount);
    }

    @DeleteMapping("/{zatchId}/dislikes")
    public PostZatchLikeRes postZatchDislike(HttpServletRequest request, @PathVariable("zatchId") Long zatchId) {
        Long userId = (Long) request.getAttribute("userId");

        Integer likeCount = zatchService.makeZatchDisLike(userId, zatchId);
        return new PostZatchLikeRes(zatchId, likeCount);

    }

    @GetMapping("/search/popularItem")
    @ApiOperation(value = "인기있는 재치 물품", notes = "좋아요 순 조회")
    public GetPopularZatchItemRes getPopularItem(HttpServletRequest request) {
        //Long userId = getUserId(request);
        List<Map<String, Object>> popularItem= zatchService.popularZatchItem();
        return new GetPopularZatchItemRes(popularItem);
    }

}
