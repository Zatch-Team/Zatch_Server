package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    User selectOneById(Long memberId);

    List<User> selectAll();

    Boolean isSignup(String email);

    String getUserId(String email);

    Long insert(User user);

    Long modifyNickname(Long userId, String newNickname);

    List<Map<String, Object>> profile(Long userId);

    String townInsert(Long userId, String town);
}
