package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApprovalRequest;
import com.example.demo.model.ApprovalStatus;
import com.example.demo.model.Status;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;
	
	public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

	public ResponseEntity<Ticket> getTicketById(int ticketId){
		Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new ResourceNotFoundException("Ticket not exist with ticketId " + ticketId));
		return ResponseEntity.ok(ticket);
	}

    public String createTicket(Ticket ticket) {
        ticket.setCreatedDate(LocalDateTime.now());
        ticket.setUpdatedDate(LocalDateTime.now());
        if (ticket.getStatus() == Status.CLOSED && ticket.getClosedDate() == null) {
            ticket.setClosedDate(LocalDateTime.now());
        }
        ticketRepository.save(ticket);
        return "Ticket inserted successfully";
    }
    
    public ResponseEntity<Ticket> reopenTicket(Integer ticketId) {
        Ticket existingTicket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not exist with ticketId " + ticketId));
        
        if (existingTicket.getStatus() == Status.CLOSED) {
            existingTicket.setStatus(Status.REOPENED);
            existingTicket.setClosedDate(null);
            existingTicket.setUpdatedDate(LocalDateTime.now());
            ticketRepository.save(existingTicket);
            return ResponseEntity.ok(existingTicket);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Ticket> updateTicket(Integer ticketId, Ticket ticket) {
    	Ticket existingTicket = ticketRepository.findById(ticketId).orElseThrow(()-> new ResourceNotFoundException("Ticket not exist with ticketId " + ticketId));
        if (existingTicket != null) {
        	existingTicket.setRequester(ticket.getRequester());
            existingTicket.setTitle(ticket.getTitle());
            existingTicket.setDescription(ticket.getDescription());
            existingTicket.setStatus(ticket.getStatus());
            if (existingTicket.getStatus() == Status.CLOSED && existingTicket.getClosedDate() == null) {
                existingTicket.setClosedDate(LocalDateTime.now());
            }
            existingTicket.setPriority(ticket.getPriority());
            existingTicket.setUpdatedDate(LocalDateTime.now());
            ticketRepository.save(existingTicket);
            return ResponseEntity.ok(existingTicket);
        }
        return null;
    }
    
    public Map<String, String> deleteTicket(int ticketId){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<Ticket> ticket = this.ticketRepository.findById(ticketId);
		if(ticket.isPresent()) {
			ticketRepository.deleteById(ticketId);
			result.put("Message", "Ticket deleted successfully");
		}else {
			result.put("Message", "Failed to delete ticket, Ticket ID is invalid");
		}
		return result;
	}
    
    public ApprovalRequest requestApproval(Integer ticketId, Integer approverId) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        Optional<User> approverOpt = userRepository.findById(approverId);

        if (ticketOpt.isPresent() && approverOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            User approver = approverOpt.get();

            ApprovalRequest approvalRequest = new ApprovalRequest();
            approvalRequest.setTicket(ticket);
            approvalRequest.setApprover(approver);
            approvalRequest.setApprovalStatus(ApprovalStatus.PENDING);
            approvalRequest.setRequestedDate(LocalDateTime.now());

            return approvalRequestRepository.save(approvalRequest);
        }

        return null; // or throw an exception
    }

    public ApprovalRequest decideApproval(Integer approvalId, boolean approve) {
        Optional<ApprovalRequest> approvalRequestOpt = approvalRequestRepository.findById(approvalId);

        if (approvalRequestOpt.isPresent()) {
            ApprovalRequest approvalRequest = approvalRequestOpt.get();
            approvalRequest.setDecisionDate(LocalDateTime.now());
            approvalRequest.setApprovalStatus(approve ? ApprovalStatus.APPROVED : ApprovalStatus.REJECTED);
            return approvalRequestRepository.save(approvalRequest);
        }

        return null; // or throw an exception
    }

    public ApprovalRequest getApprovalStatus(Integer ticketId) {
        return approvalRequestRepository.findByTicket_TicketId(ticketId);
    }
    
    public List<ApprovalRequest> getAllApprovals(){
    	return approvalRequestRepository.findAll();
    }
    
    public List<Ticket> getTicketsByLoginId(String loginId) {
        return ticketRepository.findByRequester_LoginId(loginId);
    }

}