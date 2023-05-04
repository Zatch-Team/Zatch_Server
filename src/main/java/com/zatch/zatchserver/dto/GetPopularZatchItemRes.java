package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetPopularZatchItemRes {
    List<Map<String, Object>> popularItem;
}
