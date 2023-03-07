package ru.veselov.websocketroom.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import ru.veselov.websocketroom.model.MessageModel;
import ru.veselov.websocketroom.model.SendMessage;
import ru.veselov.websocketroom.model.UserList;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@AllArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    private final WebSocketConnectToChatListener listener;
    @MessageMapping("/test/{id}")//с этого адреса на фронте приходит сообщение
    /*
    * https://stackoverflow.com/questions/27047310/path-variables-in-spring-websockets-sendto-mapping*/
    public void sendMessage(@DestinationVariable("id") String id, MessageModel model){
        log.info("Получено сообщение {}", model);
        messagingTemplate.convertAndSend("/topic/greetings/"+id,new SendMessage("Чат № "+id+"привет "+model.getName()));
    }







}
