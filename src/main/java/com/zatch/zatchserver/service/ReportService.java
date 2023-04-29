package com.zatch.zatchserver.service;

public interface ReportService {
    String reportUser(Long userId, Long reportedId, int reportReason);
}
