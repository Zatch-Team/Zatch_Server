package com.zatch.zatchserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
        // ENTER : 처음 채팅방에 들어온 상태
        // TALK : 이미 session에 연결되어 있고 채팅 중인 상태
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
