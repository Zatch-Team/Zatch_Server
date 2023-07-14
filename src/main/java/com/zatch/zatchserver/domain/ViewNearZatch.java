package com.zatch.zatchserver.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class ViewNearZatch {
    private Long categoryId;
    private Boolean isFree;
    private String mdName;
    private String content;
    private Integer quantity;
    private Date dateBuy;
    private Date dateExpire;
    private Date updatedAt;
    private Integer isOpened;
    private Integer anyZatch;
    private Integer likeCount;
    private String addrName;

    public ViewNearZatch(Long categoryId, Boolean isFree, String mdName, String content, Integer quantity, Date dateBuy, Date dateExpire, Date updatedAt, Integer isOpened, Integer anyZatch,
                         Integer likeCount, String addrName) {
        this.categoryId = categoryId;
        this.isFree = isFree;
        this.mdName = mdName;
        this.content = content;
        this.quantity = quantity;
        this.dateBuy = dateBuy;
        this.dateExpire = dateExpire;
        this.updatedAt = updatedAt;
        this.isOpened = isOpened;
        this.anyZatch = anyZatch;
        this.likeCount = likeCount;
        this.addrName = addrName;
    }
}
