package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(TicketSharingId.class)
public class TicketSharing {
	
	@Id
	private Integer ticketId;
	
	@Id
	private Integer sharedWithId;
	
	private LocalDateTime sharedDate;
	
	@ManyToOne
	@JoinColumn(name = "ticketId", insertable = false, updatable = false, nullable = false)
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name = "sharedWithId", insertable = false, updatable = false, nullable = false)
	private User sharedWith;

}
