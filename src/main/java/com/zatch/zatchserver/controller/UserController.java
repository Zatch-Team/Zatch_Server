package com.zatch.zatchserver.controller;

import com.zatch.zatchserver.DefaultRes;
import com.zatch.zatchserver.ResponseMessage;
import com.zatch.zatchserver.StatusCode;
import com.zatch.zatchserver.domain.User;
import com.zatch.zatchserver.dto.*;
import com.zatch.zatchserver.service.AuthService;
import com.zatch.zatchserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/new")
    @ApiOperation(value = "회원가입", notes = "회원가입 API")
    public ResponseEntity postUser(@RequestBody PostUserReqDto postUserReqDto, HttpServletResponse response) {
        // 이메일을 통해 회원가입 or 로그인 check
        String isSignup = userService.loginOrSignup(postUserReqDto.getEmail());

        // 회원가입
        if (isSignup.equals("signup")){
            List<String> adjectives = Arrays.asList("귀여운", "당황한", "어리둥절", "깜찍한", "동글동글", "초롱초롱", "배고픈", "의아한", "놀라운", "어여쁜", "차분한", "한가한", "화려한", "깨끗한",
                    "정직한", "활발한", "긍정적인", "낙천적인", "다정한", "단호한", "겸손한", "매력적인", "발랄한", "민첩한", "상냥한", "솔직한", "신중한", "용감한", "수줍은", "소중한");
            List<String>  animals = Arrays.asList("강아지", "거북이", "고래", "고양이", "공작", "기린", "까치", "낙타", "너구리", "늑대", "다람쥐", "부엉이", "사슴", "사자", "새우", "수달", "순록",
                    "악어", "여우", "오리", "올빼미", "청설모", "치타", "코끼리", "토끼", "팬더", "펭귄", "표범", "햄스터", "호랑이");
            Collections.shuffle(adjectives);
            Collections.shuffle(animals);

            User newUser = new User(
                    postUserReqDto.getName(),
                    postUserReqDto.getEmail(),
                    adjectives.get(0) + " " + animals.get(0)
            );

            userService.join(newUser);

            PostUserResDto postUserResDto = new PostUserResDto(newUser.getName(), newUser.getEmail(), adjectives.get(0) + " " + animals.get(0));

            // 토큰
            String email = postUserReqDto.getEmail();
            String userId = userService.getUserId(email);
            String accessToken = authService.issueAccessToken(Long.valueOf(userId));
            response.addHeader("ACCESS_TOKEN", accessToken);
            String token = userService.token(Long.valueOf(userId), accessToken);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, postUserReqDto+" / accessToken : "+token+" / userId : "+userId), HttpStatus.OK);
        }

        // 로그인
        // 토큰
        String email = postUserReqDto.getEmail();
        String userId = userService.getUserId(email);
        String accessToken = authService.issueAccessToken(Long.valueOf(userId));
        response.addHeader("ACCESS_TOKEN", accessToken);
        String token = userService.token(Long.valueOf(userId), accessToken);

        System.out.println("token >>>>> "+token);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, new GetUserReqDto(email)+" / accessToken : "+token+" / userId : "+userId), HttpStatus.OK);
    }

    @GetMapping("/logout")
    @ApiOperation(value="로그아웃", notes = "로그아웃 API")
    public ResponseEntity logout(HttpServletRequest request) throws Exception{
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.USER_NICKNAME_EDIT_SUCCESS, "Success Logout"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Logout"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{userId}/nickname")
    @ApiOperation(value = "회원 닉네임 수정", notes = "회원 닉네임 수정 API")
    public ResponseEntity patchNickname(@PathVariable("userId") Long userId
            , @RequestBody PatchUserNicknameReqDto pathUserNicknameReqDto) {
        try {
            String newNickname = pathUserNicknameReqDto.getNewNickname();
            Long idOfModifiedUser = userId;
            userService.modifyNickname(idOfModifiedUser, newNickname);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.USER_NICKNAME_EDIT_SUCCESS, new PatchUserNicknameResDto(newNickname)), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Modify Nickname"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/profile")
    @ApiOperation(value = "회원 프로필", notes = "회원 프로필 API")
    public ResponseEntity getProfile(@PathVariable("userId") Long userId) {
        try {
            String userNickname = userService.profile(userId);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.MY_PROFILE_SUCCESS, new GetProfileResDto(userNickname)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Profile"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/address")
    @ApiOperation(value = "회원 동네", notes = "회원 동네 API")
    public ResponseEntity postAddress(@PathVariable("userId") Long userId, @RequestBody PostUserAddressReqDto postUserAddressReqDTO) {
        try {
            String addr_name = postUserAddressReqDTO.getAddr_name();
            String addr_x = postUserAddressReqDTO.getAddr_x();
            String addr_y = postUserAddressReqDTO.getAddr_y();
            userService.address(userId, addr_name, addr_x, addr_y);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.USER_TOWN_SUCCESS, new PostUserAddressReqDto(addr_name, addr_x, addr_y)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error User Town"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/mypage")
    @ApiOperation(value = "회원 프로필", notes = "회원 프로필 API")
    public ResponseEntity getMypage(@PathVariable("userId") Long userId) {
        try {
            String user_id = userService.mypage(userId);
            System.out.println(user_id);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.MYPAGE_SUCCESS, new GetMypageResDto(user_id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR, "Error Profile"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
