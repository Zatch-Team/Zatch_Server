package com.zatch.zatchserver.domain;

import com.zatch.zatchserver.dto.ChatMessage;
import com.zatch.zatchserver.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (String.valueOf(chatMessage.getType()).equals("IMAGE")){
            chatService.sendImage(String.valueOf(chatMessage.getType()), chatMessage.getRoomId(), chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getImgUrl());
        }
        else {
            chatService.updateDB(String.valueOf(chatMessage.getType()), chatMessage.getRoomId(), chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getMessage());
        }
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}

