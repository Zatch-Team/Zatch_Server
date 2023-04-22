package com.zatch.zatchserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket //webSocket 활성화
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Handler와 webSocket 주소를 WebSocketHandlerRegistry에 추가 -> 해당 주소로 접근하면 웹소켓 연결 가능
        registry.addHandler(webSocketHandler, "/ws/chat/{userId}").setAllowedOrigins("*");
    }

//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(chatHandler(), "/chat/{userId}").setAllowedOrigins("*");
//    }
//
//    @Bean
//    public WebSocketHandler chatHandler() {
//        return (WebSocketHandler) new ChatHandler();
//    }
}
