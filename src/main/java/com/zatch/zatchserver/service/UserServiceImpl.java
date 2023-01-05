package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long join(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User getOneById(Long userId) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Long modifyNickname(Long userId, String newNickname) {
        return null;
    }
}
