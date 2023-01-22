package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ExchangeSearch;

import java.util.List;

public interface ExchangeSearchService {
    List<ExchangeSearch> viewAll(String itemName1, String itemName2);
}
