package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.repository.UserRepository;
import com.zatch.zatchserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "회원 조회", notes = "회원 id로 회원 조회 API")
    public GetUserResDto getUser(@PathVariable("UserId") Long userId) {
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
        Long idOfModifiedUser = userId;

        userService.modifyNickname(idOfModifiedUser, newNickname);
        return new PatchUserNicknameResDto(newNickname);
    }
}
