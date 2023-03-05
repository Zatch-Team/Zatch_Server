package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserResDto {
    String name;
    String email;
    String nickname;
}
