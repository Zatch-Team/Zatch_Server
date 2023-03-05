package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    Long insert(User user);

    String getUser(String email);

    Long modifyNickname(Long userId, String newNickname);

    List<Map<String, Object>> profile(Long userId);

    String townInsert(Long userId, String town);
}
