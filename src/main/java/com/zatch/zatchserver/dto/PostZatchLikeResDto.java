package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostZatchLikeResDto {
    private Long zatchId;
    private Integer likeCount;
    private Boolean isLiked;
}
