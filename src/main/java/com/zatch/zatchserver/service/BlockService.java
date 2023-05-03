package com.zatch.zatchserver.service;

import java.util.List;
import java.util.Map;

public interface BlockService {
    String blockUser(Long userId, Long reportedId);
    List<Map<String, Object>> blockList(Long userId);
}
