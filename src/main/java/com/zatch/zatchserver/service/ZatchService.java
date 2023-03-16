package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ExchangeSearch;
import com.zatch.zatchserver.domain.ViewMyZatch;
import com.zatch.zatchserver.domain.Zatch;
import com.zatch.zatchserver.repository.ZatchRepository;
import com.zatch.zatchserver.repository.ZatchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ZatchService {

    private final ZatchRepository zatchRepository;

    @Autowired
    public ZatchService(ZatchRepositoryImpl zatchRepository) {
        this.zatchRepository = zatchRepository;
    }

    public List<Zatch> getPostList() {
        return zatchRepository.findAllByOrderByCreatedAtDesc();
    }

    public Long register(Zatch zatch) {
        return zatchRepository.register(zatch);
    }

    public List<ViewMyZatch> getZatchName(Long userId){return zatchRepository.getZatchName(userId);}

    public List<ExchangeSearch> viewAll(String itemName1, String itemName2) {return zatchRepository.viewAll(itemName1, itemName2);}
}
