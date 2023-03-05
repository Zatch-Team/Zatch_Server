package com.zatch.zatchserver.service;

import com.zatch.zatchserver.dto.PostLoginReq;
import com.zatch.zatchserver.domain.User;

import java.util.List;

public interface UserService {
    Long join(User user);

    String getUser(String email);

    Long modifyNickname(Long userId, String newNickname);

    String profile(Long userId);

    String town(Long userId, String town);
}
