package com.zatch.zatchserver.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    String reportUser(Long userId, Long reportedId, int reportReason);
}
