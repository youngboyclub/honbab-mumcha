package yougboyclub.honbabstop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import yougboyclub.honbabstop.dto.Notification;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate template;

    @Autowired
    public NotificationController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/notify")
    public void notify(String notification) throws Exception {
        this.template.convertAndSend("/topic/notification", new Notification());
    }
}
