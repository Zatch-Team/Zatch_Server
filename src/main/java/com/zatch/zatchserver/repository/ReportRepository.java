package com.zatch.zatchserver.repository;

public interface ReportRepository {
    String postReport(Long userId, Long reportedId, int reportReason);
}
