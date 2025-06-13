package com.supermarket.service;

import com.supermarket.entity.Stock;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendLowStockNotification(Stock stock) {
        // Email notification
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("admin@supermarket.com");
        email.setSubject("Low Stock Alert");
        email.setText("Product " + stock.getProductName() + " has low stock: " + stock.getQuantity());
        mailSender.send(email);

        // WhatsApp notification
        Message.creator(
            new PhoneNumber("whatsapp:+1234567890"),
            new PhoneNumber("whatsapp:" + twilioPhoneNumber),
            "Low stock alert: " + stock.getProductName() + " has " + stock.getQuantity() + " units left."
        ).create();
    }
}
