package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// JSON -> 객체로 변환할 때 필요
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReqDto {
    String name;
    String email;
}
