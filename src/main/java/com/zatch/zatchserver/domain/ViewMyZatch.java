package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class ViewMyZatch {
    private String itemName;

    public ViewMyZatch(String itemName){
        this.itemName = itemName;
    }
}
