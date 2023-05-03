package com.zatch.zatchserver.domain;

import lombok.Getter;

@Getter
public class Notice {
    private String title;
    private String content;
    private String created_at;
    private String updated_at;
}
