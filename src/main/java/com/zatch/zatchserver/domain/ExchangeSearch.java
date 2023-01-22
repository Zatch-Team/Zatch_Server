package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class ExchangeSearch {
    private Long id;
    private Long user_id;
    private Integer is_free;
    private String item_name;
    private Integer any_zatch;
    private Integer like_count;

    /*---Constructor---*/
    public ExchangeSearch(Long user_id, Integer is_free, String item_name, Integer any_zatch, Integer like_count) {
        this.user_id = user_id;
        this.is_free = is_free;
        this.item_name = item_name;
        this.any_zatch = any_zatch;
        this.like_count = like_count;
    }
}
