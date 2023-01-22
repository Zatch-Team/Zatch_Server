package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Search;

import java.util.List;

public interface SearchService {
    List<Search> findByItemName(String item_name);
}
