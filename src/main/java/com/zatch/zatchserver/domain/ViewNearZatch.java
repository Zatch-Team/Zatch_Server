package com.zatch.zatchserver.domain;

import java.util.Date;

public class ViewNearZatch {
    private Long categoryId;
    private Boolean isFree;
    private String itemName;
    private String content;
    private String zatchImgUrl;
    private Integer quantity;
    private Date purchaseDate;
    private Date expirationDate;
    private Date updated_at;
    private Integer isOpened;
    private Integer anyZatch;
    private Integer likeCount;
    private String town1;
    private String town2;
    private String town3;

    public ViewNearZatch(Long categoryId, Boolean isFree, String itemName, String content, String zatchImgUrl
    , Integer quantity, Date purchaseDate, Date expirationDate, Date updated_at, Integer isOpened, Integer anyZatch,
                         Integer likeCount, String town1, String town2, String town3){
        this.categoryId = categoryId;
        this.isFree = isFree;
        this.itemName = itemName;
        this.content = content;
        this.zatchImgUrl = zatchImgUrl;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
        this.updated_at = updated_at;
        this.isOpened = isOpened;
        this.anyZatch = anyZatch;
        this.likeCount = likeCount;
        this.town1 = town1;
        this.town2 = town2;
        this.town3 = town3;
    }
}
