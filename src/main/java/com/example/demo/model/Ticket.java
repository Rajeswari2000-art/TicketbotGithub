package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;
	
	@ManyToOne
	@JoinColumn(name = "requesterId")
	private User requester;
	
	private String title;
	
	private String description;
	
	private Status status;
	
	private Priority priority;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	
	private LocalDateTime closedDate;

}
