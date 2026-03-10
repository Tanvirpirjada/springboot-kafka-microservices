package com.notification.notificationapi.service;


import com.notification.notificationapi.event.InventoryEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailSender(JavaMailSender mailSender){

        this.mailSender = mailSender;
    }

    // ─── Called when inventory event received ───
    public void sendInventoryEmail(InventoryEvent event) {

        System.out.println("hii i am inside the notifcation about to send email : "+event);

        if ("CONFIRMED".equals(event.getStatus())) {
            sendEmail(
                    event.getEmail(),
                    "Order Confirmed! 🎉",
                    "Hi! Your order #" + event.getOrderId() +
                            " is confirmed! Proceed to payment."
            );
        } else {
            System.out.println("hii i am inside the notifcation about to send email : "+event);

            sendEmail(
                    event.getEmail(),
                    "Order Failed ❌",
                    "Sorry! Item is out of stock for order #"
                            + event.getOrderId()
            );
        }
    }

    // ─── Called when payment event received ───
//    public void sendPaymentEmail(PaymentEvent event) {
//        if ("PAYMENT_SUCCESS".equals(event.getStatus())) {
//            sendEmail(
//                    event.getCustomerEmail(),
//                    "Payment Successful! 🎉",
//                    "Your payment for order #" + event.getOrderId() +
//                            " is successful! Your order is on its way."
//            );
//        } else {
//            sendEmail(
//                    event.getCustomerEmail(),
//                    "Payment Failed ❌",
//                    "Payment failed for order #" + event.getOrderId() +
//                            ". Please retry."
//            );
//        }
//    }

    // ─── Core email sender ───
    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
