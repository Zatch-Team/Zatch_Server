package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetBlockResDto {
    private Long blocked_id;
    private List<Map<String, Object>> blockList;
}
