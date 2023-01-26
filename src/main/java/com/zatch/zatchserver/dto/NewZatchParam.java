package com.zatch.zatchserver.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class NewZatchParam {
    private Long userId;
    private Long categoryId;
    private Boolean isFree;
    private String itemName;
    private String content;
    private Integer quantity;
    private Date purchaseDate;
    private Date expirationDate;
    private Boolean isOpened;
    private Boolean allowAnyZatch;

    @Builder
    public NewZatchParam(Long userId, Long categoryId, Boolean isFree, String itemName, String content, Integer quantity, Date purchaseDate, Date expirationDate, Boolean isOpened, Boolean allowAnyZatch) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.isFree = isFree;
        this.itemName = itemName;
        this.content = content;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
        this.isOpened = isOpened;
        this.allowAnyZatch = allowAnyZatch;
    }
}
