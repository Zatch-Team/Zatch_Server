package com.zatch.zatchserver.service;

import com.zatch.zatchserver.domain.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    List<ChatRoom> findAllRoom();
    ChatRoom createRoom(String name);
    ChatRoom findRoomById(String roomId);
    <T> void sendMessage(WebSocketSession session, T message);
    String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating);
    String updateDB(String type, String roomId, String sender, String message);
}
