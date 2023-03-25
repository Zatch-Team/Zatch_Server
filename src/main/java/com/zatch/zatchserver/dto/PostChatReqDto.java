package com.zatch.zatchserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostChatReqDto {
    Long send_user_id;
    Long receive_user_id;
    String review_context;
    int star_rating;
}
