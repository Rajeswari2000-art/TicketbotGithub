package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TicketUpdate;
import com.example.demo.repository.TicketUpdateRepository;

@Service
public class TicketUpdateService {
	
	@Autowired
	TicketUpdateRepository ticketUpdateRepository;
	
	public List<TicketUpdate> getAllUpdates() {
        return ticketUpdateRepository.findAll();
    }

	public Map<String, String> getUpdateById(int id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketUpdate> ticketUpdate = this.ticketUpdateRepository.findById(id);
		if(ticketUpdate.isPresent()) {
			result.put("Message", ticketUpdate.get().toString());
		}else {
			result.put("Message", "TicketUpdate ID is invalid");
		}
		return result;
	}

    public String createUpdate(TicketUpdate ticketUpdate) {
        ticketUpdate.setUpdateDate(LocalDateTime.now());
        ticketUpdateRepository.save(ticketUpdate);
        return "TicketUpdate inserted succesfully";
    }

    public String updateUpdate(Integer id, TicketUpdate ticketUpdate) {
        TicketUpdate existingTicketUpdate = ticketUpdateRepository.findById(id).orElse(null);
        if (existingTicketUpdate != null) {
            existingTicketUpdate.setTicket(ticketUpdate.getTicket());
            existingTicketUpdate.setUpdatedBy(ticketUpdate.getUpdatedBy());
            existingTicketUpdate.setUpdateType(ticketUpdate.getUpdateType());
            existingTicketUpdate.setUpdateDescription(ticketUpdate.getUpdateDescription());
            existingTicketUpdate.setUpdateDate(LocalDateTime.now());
            ticketUpdateRepository.save(existingTicketUpdate);
            return "TicketUPdate updated successfully";
        }
        return null;
    }
    
    public Map<String, String> deleteUpdate(int id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketUpdate> ticketUpdate = this.ticketUpdateRepository.findById(id);
		if(ticketUpdate.isPresent()) {
			ticketUpdateRepository.deleteById(id);
			result.put("Message", "TicketUpdate deleted successfully");
		}else {
			result.put("Message", "Failed to delete TicketUpdate,  TicketUpdate ID is invalid");
		}
		return result;
	}

}