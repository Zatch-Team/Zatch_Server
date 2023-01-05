package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.User;

import java.util.List;

public interface UserService {
    Long join(User user);

    User getOneById(Long userId);

    List<User> getAll();

    Long modifyNickname(Long userId, String newNickname);
}
