package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetExchangeSearchResDto {
    String itemName1; // 내 물건
    String itemName2;  // 교환할 물건
}
