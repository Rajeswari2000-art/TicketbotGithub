package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmailNotification;
import com.example.demo.model.EmailStatus;
import com.example.demo.repository.EmailNotificationRepository;

@Service
public class EmailNotificationService {
	
	@Autowired
	EmailNotificationRepository emailNotificationRepository;
	
	@Autowired
	JavaMailSender mailSender;
	
	public String createNotification(EmailNotification emailNotification) {
        emailNotificationRepository.save(emailNotification);
        sendEmail(emailNotification);
        return "Email sent successfully";
    }

    private void sendEmail(EmailNotification emailNotification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailNotification.getRecipientEmail());
        message.setSubject(emailNotification.getSubject());
        message.setText(emailNotification.getBody());
        try {
            mailSender.send(message);
            emailNotification.setEmailStatus(EmailStatus.SENT);
        } catch (Exception e) {
            emailNotification.setEmailStatus(EmailStatus.FAILED);
            e.printStackTrace();
        }
        emailNotification.setSentAt(LocalDateTime.now());
        emailNotificationRepository.save(emailNotification);
    }
    
    public List<EmailNotification> getAllNotifications(){
    	return emailNotificationRepository.findAll();
    }

}