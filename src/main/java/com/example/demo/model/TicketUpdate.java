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
public class TicketUpdate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer updateId;
	
	@ManyToOne
	@JoinColumn(name = "ticketId")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name = "updateById")	
	private User updatedBy;
	
	private UpdateType updateType;
	
	private String updateDescription;
	
	private LocalDateTime updateDate;

}
