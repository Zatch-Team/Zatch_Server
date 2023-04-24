package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository noticeRepository;
    @Override
    public List<Map<String, Object>> getAllNotice() {
        return noticeRepository.getAllNotice();
    }
}
