package com.zatch.zatchserver.repository;

import java.util.List;
import java.util.Map;

public interface BlockRepository {
    String postBlock(Long userId, Long blockedId);
    List<Map<String, Object>> getBlockList(Long userId);
}
