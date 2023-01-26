package com.zatch.zatchserver.repository;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.UserInfo;

import java.util.List;

public interface UserRepository {
    UserInfo selectOneById(Long memberId);

    List<User> selectAll();

    Long insert(User user);

    Long authenticateBy(String email, String password);
}
