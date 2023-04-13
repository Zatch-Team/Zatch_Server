package com.zatch.zatchserver.service;

public interface ChatService {
    String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating);
}
