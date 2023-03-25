package com.zatch.zatchserver.repository;

public interface ChatRepositoryImpl {
    String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating);
}
