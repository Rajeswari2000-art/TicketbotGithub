package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TicketSharing;
import com.example.demo.model.TicketSharingId;
import com.example.demo.repository.TicketSharingRepository;

@Service
public class TicketSharingService {
	
	@Autowired
	TicketSharingRepository ticketSharingRepository;
	
	public List<TicketSharing> findAllTickets() {
        return ticketSharingRepository.findAll();
    }
	
	public Map<String, String> getTicketById(TicketSharingId sharingId){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketSharing> ticketSharing = this.ticketSharingRepository.findById(sharingId);
		if(ticketSharing.isPresent()) {
			result.put("Message", ticketSharing.get().toString());
		}else {
			result.put("Message", "TicketSharing ID is invalid");
		}
		return result;
	}
	
	public String createTicket(TicketSharing ticketSharing) {
		ticketSharing.setSharedDate(LocalDateTime.now());
        ticketSharingRepository.save(ticketSharing);
        return "TicketSharing inserted successfully";
    }
	
	public String updateTicket(TicketSharingId sharingId, TicketSharing ticketSharing) {
        TicketSharing existingTicketSharing = ticketSharingRepository.findById(sharingId).orElse(null);
        if (existingTicketSharing != null) {
            existingTicketSharing.setTicket(ticketSharing.getTicket());
            existingTicketSharing.setSharedWith(ticketSharing.getSharedWith());
            ticketSharingRepository.save(existingTicketSharing);
            return "TicketSharing updated successfully";
        }
        return null;
    }

	public Map<String, String> deleteTicket(TicketSharingId sharingId){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketSharing> ticketSharing = this.ticketSharingRepository.findById(sharingId);
		if(ticketSharing.isPresent()) {
			ticketSharingRepository.deleteById(sharingId);
			result.put("Message", "TicketSharing deleted successfully");
		}else {
			result.put("Message", "Failed to delete ticketSharing, TicketSharing ID is invalid");
		}
		return result;
	}

}