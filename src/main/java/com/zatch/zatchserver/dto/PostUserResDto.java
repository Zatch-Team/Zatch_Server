package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class PostUserResDto {
    String name;
    String email;
    String nickname;
}
