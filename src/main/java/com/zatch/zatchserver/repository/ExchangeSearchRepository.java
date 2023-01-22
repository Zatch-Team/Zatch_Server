package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ExchangeSearch;

import java.util.List;

public interface ExchangeSearchRepository {
    List<ExchangeSearch> viewAll(String itemName1, String itemName2);
}
