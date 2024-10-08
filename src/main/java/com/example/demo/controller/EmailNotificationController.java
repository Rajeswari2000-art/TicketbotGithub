package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailNotification;
import com.example.demo.service.EmailNotificationService;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailNotificationController {
	
	@Autowired
	EmailNotificationService emailNotificationService;
	
	@PostMapping
    public String createEmailNotification(@RequestBody EmailNotification emailNotification) {
        return emailNotificationService.createNotification(emailNotification);
    }
	
	@GetMapping
	public List<EmailNotification> getAllNotifications(){
		return emailNotificationService.getAllNotifications();
	}

}
