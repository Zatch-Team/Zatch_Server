package com.zatch.zatchserver.repository;


import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;

import java.util.List;

public interface ZatchRepository {

    List<Zatch> findAllByOrderByCreatedAtDesc();


    Long register(Zatch zatch);

    List<ViewMyZatch> getZatchName(Long userId);

    List<ExchangeSearch> viewAll(String itemName1, String itemName2);

}


