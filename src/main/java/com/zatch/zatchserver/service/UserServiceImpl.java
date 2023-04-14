package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // S3
    @Autowired
    private S3Uploader s3Uploader;

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

    @Override
    public String uploadProfile(Long userId, MultipartFile image) {
        return userRepository.uploadProfile(image, userId);
    }

    @Override
    public String patchProfile(Long userId, MultipartFile image) {
        return userRepository.patchProfile(image, userId);
    }
}
