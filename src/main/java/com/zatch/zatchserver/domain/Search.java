package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class Search {
    private int post_zatch_id;
    //private int category_id;
    private int is_free;
    private String item_name;
    private String content;
    private int any_zatch;

    public Search(int is_free, String item_name, String content, int any_zatch){
        this.is_free=is_free;
        this.item_name=item_name;
        this.content=content;
        this.any_zatch=any_zatch;
    }

}
