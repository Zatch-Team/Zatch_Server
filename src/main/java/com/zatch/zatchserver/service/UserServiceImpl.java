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
    public String loginOrSignup(String email) {
        return userRepository.isSignup(email);
    }

    @Override
    public Long join(User user) {
        return userRepository.insert(user);
    }

    @Override
    public String getUserId(String email) {
        return userRepository.getUserId(email);
    }

    @Override
    public User getOneById(Long userId) {
        return userRepository.selectOneById(userId);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Long modifyNickname(Long userId, String newNickname) {
        return userRepository.modifyNickname(userId, newNickname);
    }

    @Override
    public String profile(Long userId) {
        return String.valueOf(userRepository.profile(userId));
    }

    @Override
    public String town(Long userId, String town) {
        return userRepository.townInsert(userId, town);
    }

    @Override
    public String token(Long userId, String token) {
        return userRepository.insertToken(userId, token);
    }

    @Override
    public String mypage(Long userId) {
        return String.valueOf(userRepository.getMypage(userId));
    }
}
