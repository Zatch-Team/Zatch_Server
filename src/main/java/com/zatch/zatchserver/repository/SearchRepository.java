package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.Search;

import java.util.List;

public interface SearchRepository {
    List<Search> findByItemName(String keyword);
}
