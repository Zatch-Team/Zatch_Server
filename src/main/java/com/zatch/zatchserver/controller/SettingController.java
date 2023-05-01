package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.dto.GetCenterResDto;
import com.zatch.zatchserver.dto.PostSettingReqDto;
import com.zatch.zatchserver.dto.PostUserReqDto;
import com.zatch.zatchserver.service.SettingService;
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
@RequestMapping("/setting")
public class SettingController {

    private final SettingService settingService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PostSettingReqDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @PostMapping("/alert_agree/{userId}")
    @ApiOperation(value = "고객센터", notes = "고객센터 API")
    public ResponseEntity getProfile(@PathVariable("userId") Long userId, @RequestBody PostSettingReqDto postSettingReqDto) {
        try {
            Boolean alert_agree = settingService.alertAgree(userId, postSettingReqDto.getAlertAgree());
            if (alert_agree) {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SETTING_ALERT_AGREE_SUCCESS, new PostSettingReqDto(true)), HttpStatus.OK);
            } else {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SETTING_ALERT_DISAGREE_SUCCESS, new PostSettingReqDto(false)), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Setting Alert Agree"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
