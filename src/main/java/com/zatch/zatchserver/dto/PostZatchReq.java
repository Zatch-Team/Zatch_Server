package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostZatchReq {
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
}
