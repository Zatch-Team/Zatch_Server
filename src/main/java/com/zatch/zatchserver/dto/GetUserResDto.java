package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResDto {
    private String name;
    private String nickname;
    private String email;
}
