package com.zatch.zatchserver.service;

import com.zatch.zatchserver.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating) {
        return chatRepository.after_deal(send_user_id, receive_user_id, review_context, star_rating);
    }
}
