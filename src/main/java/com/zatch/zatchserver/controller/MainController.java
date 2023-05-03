package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.service.MainService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/main")
public class MainController {
    private final MainService mainService;

//    //메인페이지에 선택한 동네 띄우기
//    @GetMapping("/myTown")
//    public List<??> getMyTown(Long userId){
//        return mainService.getNearZatch(userId);
//
//    }

    //메인페이지 내 주변 재치 조회 리스트
    @GetMapping("/{userId}/viewNearZatch")
    @ApiOperation(value = "메인페이지 내 주변 재치 조회", notes = "내 주변 재치 조회 API")
    public ResponseEntity getNearZatch(@PathVariable("userId") Long userId) {
        try {
            mainService.getNearZatch(userId);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_NEAR_ZATCH_SUCCESS, userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.GET_NEAR_ZATCH_FAIL, "Error Get Zatch"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
