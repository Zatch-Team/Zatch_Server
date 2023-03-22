package com.zatch.zatchserver.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Date;

@Getter
public class Zatch {
    private Long id;
    private Long userId;
    private Long categoryId;
    private Boolean isFree;
    private String itemName;
    private String content;
    private Integer quantity;
    private Date purchaseDate;
    private Date expirationDate;
    private Integer isOpened;
    private Integer anyZatch;
    private Integer likeCount;

    /*---Constructor---*/
    @Builder
    public Zatch(Long userId, Long categoryId, Boolean isFree, String itemName, String content, Integer quantity, Date purchaseDate, Date expirationDate, Integer isOpened, Integer anyZatch) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.isFree = isFree;
        this.itemName = itemName;
        this.content = content;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
        this.isOpened = isOpened;
        this.anyZatch = anyZatch;
    }
}

