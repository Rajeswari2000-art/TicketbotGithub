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
public class ApprovalRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer approvalId;

	@ManyToOne
	@JoinColumn(name = "ticketId")
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "approverId")
	private User approver;

	private ApprovalStatus approvalStatus;

	private LocalDateTime requestedDate;

	private LocalDateTime decisionDate;

}