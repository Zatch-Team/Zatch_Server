package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.Zatch;
import com.zatch.zatchserver.repository.ZatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ZatchService {

    private final ZatchRepository zatchRepository;

    public List<Zatch> getPostList() {
        return zatchRepository.findAllByOrderByCreatedAtDesc();
    }

    public Long register(Zatch newZatch) {
        return null;
    }
}
