package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping
	public List<Ticket> getAllTickets() {
		return ticketService.getAllTickets();
	}

	@GetMapping("/{ticketId}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable int ticketId) {
		return ticketService.getTicketById(ticketId);
	}

	@PostMapping
	public String createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}

	@PatchMapping("/{ticketId}/reopen")
	public ResponseEntity<Ticket> reopenTicket(@PathVariable Integer ticketId) {
		return ticketService.reopenTicket(ticketId);
	}

	@PutMapping("/{ticketId}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable Integer ticketId, @RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticketId, ticket);
	}

	@DeleteMapping("/{ticketId}")
	public Map<String, String> deleteTicket(@PathVariable int ticketId) {
		return this.ticketService.deleteTicket(ticketId);
	}

	@PostMapping("/{ticketId}/requestApproval")
	public ApprovalRequest requestApproval(@PathVariable Integer ticketId, @RequestParam Integer approverId) {
		return ticketService.requestApproval(ticketId, approverId);
	}

	@PostMapping("/{approvalId}/decision")
	public ApprovalRequest decideApproval(@PathVariable Integer approvalId, @RequestParam boolean approve) {
		return ticketService.decideApproval(approvalId, approve);
	}

	@GetMapping("/{ticketId}/approvalStatus")
	public ApprovalRequest getApprovalStatus(@PathVariable Integer ticketId) {
		return ticketService.getApprovalStatus(ticketId);
	}
	
	@GetMapping("/approvals")
	public List<ApprovalRequest> getAllApprovals(){
		return ticketService.getAllApprovals();
	}
	
	@GetMapping("/user/{loginId}")
	public ResponseEntity<List<Ticket>> getTicketsByLoginId(@PathVariable String loginId) {
	    List<Ticket> tickets = ticketService.getTicketsByLoginId(loginId);
	    return ResponseEntity.ok(tickets);
	}

}