package com.zatch.zatchserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zatch.zatchserver.domain.ChatRoom;
import com.zatch.zatchserver.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    private final ChatRepository chatRepository;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        System.out.println("chatRooms : "+ chatRooms.values());
        return chatRoom;
    }

    @Override
    public List<Map<String, Object>> getAllChatRoom(String userId) {
        return chatRepository.selectAllChatRoom(userId);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String after_deal(Long send_user_id, Long receive_user_id, String review_context, int star_rating) {
        return chatRepository.after_deal(send_user_id, receive_user_id, review_context, star_rating);
    }

    @Override
    public String updateDB(String type, String roomId, String sender, String receiver, String message) {
        return chatRepository.updateDB(type, roomId, sender, receiver, message);
    }

    @Override
    public String sendImage(String type, String roomId, String sender, String receiver, String imgUrl) {
        return chatRepository.sendImage(type, roomId, sender, receiver, imgUrl);
    }

    @Override
    public String outChatRoom(String userId, String roomId) {
        return chatRepository.deleteChatRoom(userId, roomId);
    }

    @Override
    public List<Map<String, Object>> profileChatRoom(String userId, String roomId) {
        return chatRepository.getProfileChatRoom(userId, roomId);
    }
}
