package com.zatch.zatchserver.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserRes {
    private String name;
    private String nickname;
    private String email;
}
