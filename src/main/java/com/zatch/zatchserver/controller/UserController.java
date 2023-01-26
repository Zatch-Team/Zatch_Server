package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.UserInfo;
import com.zatch.zatchserver.dto.controller.*;
import com.zatch.zatchserver.service.AuthService;
import com.zatch.zatchserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signin")
    public void login(@RequestBody PostLoginReq postLoginReq, HttpServletResponse response) {
        Long userId = userService.authenticate(postLoginReq.getEmail(), postLoginReq.getPassword());
        String accessToken = authService.issueAccessToken(userId);
        response.addHeader("access_token", accessToken);
    }
    @GetMapping("/informations")
    @ApiOperation(value = "회원 조회", notes = "회원 id로 회원 조회 API")
    public GetUserRes getUserInformation(HttpServletRequest request) {
        Long userId = getUserId(request);
        UserInfo findUserInfo = userService.getOneById(userId);

        return new GetUserRes(findUserInfo.getName(), findUserInfo.getNickname(), findUserInfo.getEmail());
    }

    private Long getUserId(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        log.info("userId : " + userId);
        return userId;
    }

//    @GetMapping("/all")
//    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원 조회 API")
//    public List<GetUserResDto> getAllUsers() {
//        List<User> findUsers = userService.getAll();
//
//        List<GetUserResDto> getUserResDtos = new ArrayList<>();
//        for (User findUser : findUsers) {
//            getUserResDtos.add(new GetUserResDto(findUser.getName(), findUser.getNickname(),
//                    findUser.getEmail()));
//        }
//
//        return getUserResDtos;
//    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    public PostUserRes postUser(@RequestBody PostUserReq postUserReqDto) {
        User newUser = new User(
                postUserReqDto.getName(),
                postUserReqDto.getNickname(),
                postUserReqDto.getEmail(),
                postUserReqDto.getPassword()
        );

        userService.join(newUser);

        return new PostUserRes(newUser.getName(), newUser.getEmail());
    }

    @PatchMapping("/{userId}/nickname")
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정 API")
    public PatchUserNicknameRes patchNickname(@PathVariable("userId") Long userId
            , @RequestBody PatchUserNicknameReq pathUserNicknameReqDto) {
        String newNickname = pathUserNicknameReqDto.getNewNickname();
        Long idOfModifiedUser = userService.modifyNickname(userId, newNickname);

        UserInfo modifiedUserInfo = userService.getOneById(idOfModifiedUser);
        return new PatchUserNicknameRes(modifiedUserInfo.getNickname());
    }
}
