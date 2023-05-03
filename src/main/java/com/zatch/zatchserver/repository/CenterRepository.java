package com.zatch.zatchserver.repository;

import java.util.List;
import java.util.Map;

public interface CenterRepository {
    List<Map<String, Object>> getList(Long menu);
}
