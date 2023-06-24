package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.ViewNearZatch;
import com.zatch.zatchserver.domain.ViewPopularZatch;

import java.util.List;

public interface MainRepository {
    List<ViewNearZatch> getNearZatch(Long userId);
    List<ViewPopularZatch> getPopularZatch(Long userId);
    String getMainTown(Long userId);
}
