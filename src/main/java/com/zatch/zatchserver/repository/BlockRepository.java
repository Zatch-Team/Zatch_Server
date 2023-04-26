package com.zatch.zatchserver.repository;

public interface BlockRepository {
    String postBlock(Long userId, Long blockedId);
}
