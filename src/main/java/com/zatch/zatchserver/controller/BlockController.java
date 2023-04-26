package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.dto.PostBlockReqDto;
import com.zatch.zatchserver.dto.PostReportReqDto;
import com.zatch.zatchserver.service.BlockService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/block")
public class BlockController {
    private final BlockService blockService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostBlockReqDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @PostMapping
    @ApiOperation(value = "차단하기", notes = "차단하기 API")
    public ResponseEntity blockUser(@RequestBody PostBlockReqDto postBlockReqDto) {
        try {
            Long userId = postBlockReqDto.getBlocker_id();
            Long reportedId = postBlockReqDto.getBlocked_id();
            if (!(postBlockReqDto.equals(null))){
                blockService.blockUser(postBlockReqDto.getBlocker_id(), postBlockReqDto.getBlocked_id());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BLOCK_SUCCESS, new PostBlockReqDto(userId, reportedId)), HttpStatus.OK);
            }
            else {
                return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.BLOCK_NULL, "Block NULL"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Block"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
