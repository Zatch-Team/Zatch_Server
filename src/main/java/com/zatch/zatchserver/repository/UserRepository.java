package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;

import java.util.List;

public interface UserRepository {
    User selectOneById(Long memberId);

    List<User> selectAll();

    Long insert(User user);
}
