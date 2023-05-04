package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;

import java.util.List;
import java.util.Map;

public interface ZatchRepository {

    List<Zatch> findAllByOrderByCreatedAtDesc();


    Long register(Zatch zatch);

    List<ViewMyZatch> getZatchName(Long userId);

    List<ExchangeSearch> viewAll(String itemName1, String itemName2);

    Integer increaseLike(Long userId, Long zatchId);

    Integer decreaseLike(Long userId, Long zatchId);

    List<Map<String, Object>> showPopularZatch();
}


