package com.zatch.zatchserver.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPopularZatchItemRes {
    List<String> popularItem;
}
