package com.zatch.zatchserver.repository;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ChatRepositoryImpl {
    List<Map<String, Object>> selectAllChatRoom(String userId);
    String updateDB(String type, String roomId, String sender, String receiver, String message);
    String sendImage(String type, String roomId, String sender, String receiver, String imgUrl);
    String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating);
}
