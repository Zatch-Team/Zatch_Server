package com.zatch.zatchserver.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// JSON -> 객체로 변환할 때 필요
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    String name;
    String nickname;
    String email;
    String password;
}
