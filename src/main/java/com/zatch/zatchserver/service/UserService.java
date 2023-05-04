package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    String loginOrSignup(String email);

    Long join(User user);

    String getUserId(String email);

    User getOneById(Long userId);

    List<User> getAll();

    String getNickname(String email);

    Long modifyNickname(Long userId, String newNickname);

    String profile(Long userId);

    String address(Long userId, String addr_name, String addr_x, String addr_y);

    String token(Long userId, String token);

    String mypage(Long userId);

    String uploadProfile(Long userId, MultipartFile image);

    String patchProfile(Long userId, MultipartFile image);

    Long deleteUser(Long userId);
}
