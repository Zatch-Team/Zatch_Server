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
    public String address(Long userId, String addr_name, String addr_x, String addr_y) {
        return userRepository.addressInsert(userId, addr_name, addr_x, addr_y);
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
