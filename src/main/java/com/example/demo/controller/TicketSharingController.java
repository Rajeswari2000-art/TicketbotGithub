package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketSharing;
import com.example.demo.model.TicketSharingId;
import com.example.demo.service.TicketSharingService;

@RestController
@RequestMapping("/shares")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketSharingController {
	
	@Autowired
	TicketSharingService ticketSharingService;
	
	@PostMapping
	public String createTicket(@RequestBody TicketSharing ticketSharing) {
	   return ticketSharingService.createTicket(ticketSharing);
	}

	@GetMapping
	public ResponseEntity<List<TicketSharing>> getAllTickets() {
	   return ResponseEntity.ok(ticketSharingService.findAllTickets());
	}
	
	@GetMapping("/{sharingId}")
	public Map<String, String> getTicketById(@PathVariable TicketSharingId sharingId){
	   return this.ticketSharingService.getTicketById(sharingId);
    }
	
	@PutMapping("/{sharingId}")
    public String updateTicket(@PathVariable TicketSharingId sharingId, @RequestBody TicketSharing ticketSharing) {
        return ticketSharingService.updateTicket(sharingId, ticketSharing);
    }

	@DeleteMapping("/{sharingId}")
    public Map<String, String> deleteTicket(@PathVariable TicketSharingId sharingId){
		return this.ticketSharingService.deleteTicket(sharingId);
	}

}
