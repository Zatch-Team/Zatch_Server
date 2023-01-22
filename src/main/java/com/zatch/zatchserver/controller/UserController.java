package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.config.SessionManager;
import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.repository.UserRepository;
import com.zatch.zatchserver.service.AuthService;
import com.zatch.zatchserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody PostLoginReq postLoginReq, HttpServletResponse response) {
        Long userId = userService.authenticate(postLoginReq);
        String accessToken = authService.issueAccessToken(userId);
        response.addHeader("ACCESS_TOKEN", accessToken);
    }
    @GetMapping("/{userId}")
    @ApiOperation(value = "회원 조회", notes = "회원 id로 회원 조회 API")
    public GetUserResDto getUser(@PathVariable("userId") Long userId) {
        User findUser = userService.getOneById(userId);

        return new GetUserResDto(findUser.getName(), findUser.getNickname(), findUser.getEmail());
    }

    @GetMapping("/all")
    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원 조회 API")
    public List<GetUserResDto> getAllUsers() {
        List<User> findUsers = userService.getAll();

        List<GetUserResDto> getUserResDtos = new ArrayList<>();
        for (User findUser : findUsers) {
            getUserResDtos.add(new GetUserResDto(findUser.getName(), findUser.getNickname(),
                    findUser.getEmail()));
        }

        return getUserResDtos;
    }

    @PostMapping("/new")
    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    public PostUserResDto postUser(@RequestBody PostUserReqDto postUserReqDto) {
        User newUser = new User(
                postUserReqDto.getName(),
                postUserReqDto.getNickname(),
                postUserReqDto.getEmail(),
                postUserReqDto.getPassword()
        );

        userService.join(newUser);

        return new PostUserResDto(newUser.getName(), newUser.getEmail());
    }

    @PatchMapping("/{userId}/nickname")
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정 API")
    public PatchUserNicknameResDto patchNickname(@PathVariable("userId") Long userId
            , @RequestBody PatchUserNicknameReqDto pathUserNicknameReqDto) {
        String newNickname = pathUserNicknameReqDto.getNewNickname();
        Long idOfModifiedUser = userService.modifyNickname(userId, newNickname);

        User modifiedUser = userService.getOneById(idOfModifiedUser);
        return new PatchUserNicknameResDto(modifiedUser.getNickname());
    }
}
