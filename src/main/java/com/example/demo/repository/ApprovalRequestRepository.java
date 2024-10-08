package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ApprovalRequest;

@Repository
public interface ApprovalRequestRepository extends JpaRepository<ApprovalRequest, Integer>{
	
	ApprovalRequest findByTicket_TicketId(Integer ticketId);

}