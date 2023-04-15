package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ViewNearZatch;

import java.util.List;

public interface MainRepository {
    List<ViewNearZatch> getNearZatch(Long userId);
}
