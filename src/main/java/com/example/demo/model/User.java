package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String loginId;
	
	private String passwordHash;
	
	private String name;
	
	private String email;
	
	private UserRole role;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

}
