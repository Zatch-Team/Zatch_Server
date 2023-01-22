package com.zatch.zatchserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@Data
public class Post {


    private Long id;
    private Long user_id;
    private Long category_id;
    private BigInteger is_free;
    private String item_name;
    private String content;
    private Integer quantity;
    private Date date_buy;
    private Date date_expire;
    private Integer is_opened;
    private Integer any_zatch;
    private Integer like_count;

    /*---Constructor---*/
    public Post(Long user_id, Long category_id, int is_free, String item_name, String content, int quantity, java.sql.Date date_buy, java.sql.Date date_expire,
                int is_opened, int any_zatch) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.is_free = BigInteger.valueOf(is_free);
        this.item_name = item_name;
        this.content = content;
        this.quantity = quantity;
        this.date_buy = date_buy;
        this.date_expire = date_expire;
        this.is_opened = is_opened;
        this.any_zatch=any_zatch;

    }



}
