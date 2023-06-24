package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ViewNearZatch;
import com.zatch.zatchserver.domain.ViewPopularZatch;
import com.zatch.zatchserver.repository.MainRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {
    private final MainRepositoryImpl mainRepository;

    public List<ViewNearZatch> getNearZatch(Long userId){return mainRepository.getNearZatch(userId);}

    public List<ViewPopularZatch> getPopularZatch(Long userId){return mainRepository.getPopularZatch(userId);}

}
