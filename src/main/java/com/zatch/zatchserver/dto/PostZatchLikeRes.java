package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostZatchLikeRes {
    private Long zatchId;
    private Integer likeCount;
}
