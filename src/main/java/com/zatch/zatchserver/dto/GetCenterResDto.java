package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetCenterResDto {
    private List<Map<String, Object>> list;
}
