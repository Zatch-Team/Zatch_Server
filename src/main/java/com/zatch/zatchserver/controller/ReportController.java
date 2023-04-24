package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.dto.GetNoticeResDto;
import com.zatch.zatchserver.dto.PostReportReqDto;
import com.zatch.zatchserver.service.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostReportReqDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @PostMapping
    @ApiOperation(value = "신고하기", notes = "신고하기 API")
    public ResponseEntity reportUser(@RequestBody PostReportReqDto postReportReqDto) {
        try {
            Long userId = postReportReqDto.getReporter_id();
            Long reportedId = postReportReqDto.getReported_id();
            int reason = postReportReqDto.getReport_reason();
            if (!(postReportReqDto.equals(null))){
                reportService.reportUser(postReportReqDto.getReporter_id(), postReportReqDto.getReported_id(), postReportReqDto.getReport_reason());
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.REPORT_SUCCESS, new PostReportReqDto(userId, reportedId, reason)), HttpStatus.OK);
            }
            else {
                return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.REPORT_NULL, "Report NULL"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Report"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
