package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.dto.GetNoticeResDto;
import com.zatch.zatchserver.dto.GetProfileResDto;
import com.zatch.zatchserver.service.NoticeService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetNoticeResDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @GetMapping
    @ApiOperation(value = "공지사항", notes = "공지사항 API")
    public ResponseEntity getNocite() {
        try {
            List<Map<String, Object>> notice = noticeService.getAllNotice();
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.NOTICE_ERROR, new GetNoticeResDto(notice)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Profile"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
