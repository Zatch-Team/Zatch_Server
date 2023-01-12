package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPostReqDto {

    Long user_id;
    Long category_id;
    BigInteger is_free;
    String item_name;
    String content;
    Integer quantity;
    Date date_buy;
    Date date_expire;
    Integer is_opened;
    Integer any_zatch;

}
