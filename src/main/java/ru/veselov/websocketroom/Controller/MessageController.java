package ru.veselov.websocketroom.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.veselov.websocketroom.model.MessageModel;
import ru.veselov.websocketroom.model.SendMessage;

@RestController
@CrossOrigin
@Slf4j
public class MessageController {


    @MessageMapping("/test/{id}")//это ссылка чата на фронте, куда будет слаться сообщение с бэка
    @SendTo("/topic/greetings/9")//это топик в который шлются сообщения
    /*
    * https://stackoverflow.com/questions/27047310/path-variables-in-spring-websockets-sendto-mapping*/
    public SendMessage sendMessage(@DestinationVariable("id") String id, MessageModel model){
        log.info("Получено сообщение {}", model);
        return new SendMessage("Чат № "+id+"привет"+model.getName());
    }
}
