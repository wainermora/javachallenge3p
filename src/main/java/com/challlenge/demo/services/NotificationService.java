package com.challlenge.demo.services;

import com.challlenge.demo.dto.FrontEndMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message) {
        FrontEndMessage response = new FrontEndMessage(message);

        messagingTemplate.convertAndSend("/notifications/message", response);
    }

}
