package com.ThuT.TreasureHunter.handler;

import org.slf4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GameWebSocketHandler extends TextWebSocketHandler {
//    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        sessions.add(session);
//        System.out.println("Player connected: " + session.getId());
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        System.out.println("Received: " + payload);
//
//        // Broadcast lại cho các session khác
//        for (WebSocketSession s : sessions) {
//            if (s.isOpen() && !s.equals(session)) {
//                s.sendMessage(new TextMessage(payload));
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        sessions.remove(session);
//        System.out.println("Player disconnected: " + session.getId());
//    }
private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Gửi lại cho tất cả
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                Logger logger = org.slf4j.LoggerFactory.getLogger(GameWebSocketHandler.class);
                logger.info("Sending message to session: " + s.getId() + " - Message: " + message.getPayload());
                s.sendMessage(message);
            }
        }
    }
}
