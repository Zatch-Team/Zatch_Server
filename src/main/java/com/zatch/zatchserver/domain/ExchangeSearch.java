package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class ExchangeSearch {
    private Long id;
    private Long userId;
    private Integer isFree;
    private String itemName;
    private Integer anyZatch;
    private Integer likeCount;

    /*---Constructor---*/
    public ExchangeSearch(Long userId, Integer isFree, String itemName, Integer anyZatch, Integer likeCount) {
        this.userId = userId;
        this.isFree = isFree;
        this.itemName = itemName;
        this.anyZatch = anyZatch;
        this.likeCount = likeCount;
    }
}
