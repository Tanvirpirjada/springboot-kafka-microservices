package com.notification.notificationapi.consumer;


import com.notification.notificationapi.event.InventoryEvent;
import com.notification.notificationapi.service.EmailSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final EmailSender emailSender;

    public NotificationConsumer( EmailSender emailSender){
        this.emailSender = emailSender;
    }

    @KafkaListener(topics = {"inventory-confirmed", "inventory-failed"}, groupId = "notification-group")
    public void consume(InventoryEvent event) {
        System.out.println("hii i am inside the notifcation");

        emailSender.sendInventoryEmail(event);
    }
}
