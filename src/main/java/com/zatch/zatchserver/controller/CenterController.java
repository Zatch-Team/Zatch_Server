package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.dto.GetCenterResDto;
import com.zatch.zatchserver.dto.GetProfileResDto;
import com.zatch.zatchserver.service.CenterService;
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
@RequestMapping("/center")
public class CenterController {
    private final CenterService centerService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GetCenterResDto.class,
                    examples = @Example(@ExampleProperty(value = "{'property1': 'value1', 'property2': 'value2'}", mediaType = MediaType.APPLICATION_JSON_VALUE)))
    })
    @GetMapping("/{menu}")
    @ApiOperation(value = "고객센터", notes = "고객센터 API")
    public ResponseEntity getProfile(@PathVariable("menu") Long menu) {
        try {
            List<Map<String, Object>> list = centerService.list(menu);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CENTER_LIST_SUCCESS, new GetCenterResDto(list)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Center List"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
