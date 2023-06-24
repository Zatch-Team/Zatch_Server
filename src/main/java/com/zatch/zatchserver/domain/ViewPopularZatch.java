package com.zatch.zatchserver.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class ViewPopularZatch {
    private Long categoryId;
    private Boolean isFree;
    private String mdName;
    private String content;
    private Integer quantity;
    private Date dateBuy;
    private Date dateExpire;
    private Date updated_at;
    private Integer isOpened;
    private Integer anyZatch;
    private Integer likeCount;

    public ViewPopularZatch(Long categoryId, Boolean isFree, String mdName, String content
            , Integer quantity, Date dateBuy, Date dateExpire, Date updated_at, Integer isOpened, Integer anyZatch,
                            Integer likeCount) {
        this.categoryId = categoryId;
        this.isFree = isFree;
        this.mdName = mdName;
        this.content = content;
        this.quantity = quantity;
        this.dateBuy = dateBuy;
        this.dateExpire = dateExpire;
        this.updated_at = updated_at;
        this.isOpened = isOpened;
        this.anyZatch = anyZatch;
        this.likeCount = likeCount;
    }
}
