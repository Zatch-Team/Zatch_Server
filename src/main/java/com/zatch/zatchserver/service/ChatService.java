package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ChatRoom;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;

public interface ChatService {
    List<ChatRoom> findAllRoom();
    ChatRoom createRoom(String name);
    List<Map<String, Object>> getAllChatRoom(String userId);
    ChatRoom findRoomById(String roomId);
    <T> void sendMessage(WebSocketSession session, T message);
    String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating);
    String updateDB(String type, String roomId, String sender, String receiver, String message);
    String sendImage(String type, String roomId, String sender, String receiver, String imgUrl);
}
