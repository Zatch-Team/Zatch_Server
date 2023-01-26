package com.zatch.zatchserver.service;

import com.zatch.zatchserver.dto.UserInfo;
import com.zatch.zatchserver.dto.controller.PostLoginReq;
import com.zatch.zatchserver.domain.User;

import java.util.List;

public interface UserService {
    Long join(User user);

    UserInfo getOneById(Long userId);

    List<User> getAll();

    Long modifyNickname(Long userId, String newNickname);

    Long authenticate(String email, String password);
}
