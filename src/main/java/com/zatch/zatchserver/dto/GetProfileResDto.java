package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProfileResDto {
    private String nickname;
    // 별점
    // 한 줄 후기 리스트
    // 나의 재치 현황 리스트
}
