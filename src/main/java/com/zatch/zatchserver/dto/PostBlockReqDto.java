package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostBlockReqDto {
    private Long blocked_id;
    private Long blocker_id;
}
