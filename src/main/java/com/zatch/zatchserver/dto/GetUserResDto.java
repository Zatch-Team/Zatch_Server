package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@AllArgsConstructor
@ApiModel(description = "User 정보")
public class GetUserResDto {
    @ApiModelProperty(value = "User Id", example = "1")
    private Long user_id;

    @ApiModelProperty(value = "User 이름", example = "김수정")
    private String name;

    @ApiModelProperty(value = "User 닉네임", example = "행복한 수달")
    private String nickname;

    @ApiModelProperty(value = "User 이메일", example = "email123@sungshin.ac.kr")
    private String email;

    @ApiModelProperty(value = "User 토큰", example = "abcdefg1234567")
    private String token;
}
