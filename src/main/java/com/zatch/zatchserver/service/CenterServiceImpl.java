package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CenterServiceImpl implements CenterService{

    private final CenterRepository centerRepository;

    @Override
    public List<Map<String, Object>> list(Long menu) {
        return centerRepository.getList(menu);
    }
}
