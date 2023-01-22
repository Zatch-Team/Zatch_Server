package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.repository.ExchangeSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExchangeSearchServiceImpl implements ExchangeSearchService{

    private final ExchangeSearchRepository searchRepository;

    public List<ExchangeSearch> viewAll(String itemName1, String itemName2) {return searchRepository.viewAll(itemName1, itemName2);}
}
