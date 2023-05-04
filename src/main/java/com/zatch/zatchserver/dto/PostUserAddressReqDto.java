package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserAddressReqDto {
    String addr_name;
    String addr_x;
    String addr_y;
}
