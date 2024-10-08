package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@IdClass(TicketCategoryId.class)
public class TicketCategory {
	
	@Id
	private Integer ticketId;
	
	@Id
	private Integer categoryId;
	
	@ManyToOne
	@JoinColumn(name = "ticketId", insertable = false, updatable = false, nullable = false)
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", insertable = false, updatable = false, nullable = false)
	private ProblemCategory problemCategory;

}
