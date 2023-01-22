package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Search;
import com.zatch.zatchserver.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService{

    private final SearchRepository searchRepository;

    @Override
    public List<Search> findByItemName(String keyword) {
        return searchRepository.findByItemName(keyword);
    }
}
