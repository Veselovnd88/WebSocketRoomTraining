package ru.veselov.websocketroom.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Component
@Slf4j
public class WebSocketConnectToChatListener {
    private final SimpMessagingTemplate messagingTemplate;

    private final List<String> users = new ArrayList<>();
    @Autowired
    public WebSocketConnectToChatListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @EventListener
    public StompHeaderAccessor handleConnectUser(SessionSubscribeEvent session){
        log.info("Connected");

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(session.getMessage());
        headerAccessor.getDestination();//FIXME отсюда можно взять назначение

        return headerAccessor;
    }

}
