package com.zatch.zatchserver.controller;


import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.domain.Zatch;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.service.ZatchService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zatch")
public class ZatchController {

    private final ZatchService postService;

    //메인페이지 조회 리스트
    @GetMapping("")
    public List<Zatch> getPost() {
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
                postZatchReq.getAnyZatch(),
                postZatchReq.getLikeCount()
        );

        postService.register(newPost);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostZatchLikeRes.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @PostMapping("/{zatchId}/likes")
    @ApiOperation(value = "좋아요", notes = "좋아요")
    public PostZatchLikeRes postZatchlike(HttpServletRequest request, @PathVariable("zatchId") Long zatchId) {
        Long userId = (Long) request.getAttribute("userId");
        Integer likeCount = postService.makeZatchLike(userId, zatchId);
        return new PostZatchLikeRes(zatchId, likeCount);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostZatchLikeRes.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @DeleteMapping("/{zatchId}/dislikes")
    @ApiOperation(value = "좋아요 취소", notes = "좋아요 취소")
    public PostZatchLikeRes postZatchDislike(HttpServletRequest request, @PathVariable("zatchId") Long zatchId) {
        Long userId = (Long) request.getAttribute("userId");
        Integer likeCount = postService.makeZatchDisLike(userId, zatchId);
        return new PostZatchLikeRes(zatchId, likeCount);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetPopularZatchItemRes.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @GetMapping("/search/popularItem")
    @ApiOperation(value = "인기있는 재치 물품", notes = "좋아요 순 조회")
    public GetPopularZatchItemRes getPopularItem(HttpServletRequest request) {
        List<Map<String, Object>> popularItem= postService.popularZatchItem();
        return new GetPopularZatchItemRes(popularItem);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetMyZatchResDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    //내 재치 검색어 띄우기
    @GetMapping("/{userId}/search")
    @ApiOperation(value = "교환할 수 있는 재치", notes = "검색 시, 내 재치 조회 API")
    public ResponseEntity getMyZatch(@PathVariable("userId") Long userId) {
        try {
            postService.getZatchName(userId);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ZATCH_SEARCH_SUCCESS, userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ZATCH_SEARCH_FAIL, "Get Search Fail"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetExchangeSearchResDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    //교환할 재치 검색 결과
    @GetMapping("/{userId}/search/{itemName1}&{itemName2}")
    @ApiOperation(value = "교환할 재치", notes = "교환 재치 검색 결과 조회 API")
    public ResponseEntity getSearch(@PathVariable("itemName1") String itemName1, @PathVariable("itemName2") String itemName2) {
        try {
            postService.viewAll(itemName1, itemName2);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_SEARCH_RESULT_SUCCESS, itemName1 + " " + itemName2), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_SEARCH_RESULT_FAIL, "Get Search Result Fail"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
