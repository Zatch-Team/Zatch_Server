package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;

    @Override
    public String reportUser(Long userId, Long reportedId, int reportReason) {
        return reportRepository.postReport(userId, reportedId, reportReason);
    }
}
