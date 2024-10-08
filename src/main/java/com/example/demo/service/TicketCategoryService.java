package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TicketCategory;
import com.example.demo.model.TicketCategoryId;
import com.example.demo.repository.TicketCategoryRepository;

@Service
public class TicketCategoryService {
	
	@Autowired
    TicketCategoryRepository ticketCategoryRepository;

    public List<TicketCategory> findAllCategories() {
        return ticketCategoryRepository.findAll();
    }
    
    public Map<String, String> getCategoryById(TicketCategoryId id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketCategory> ticketCategory = this.ticketCategoryRepository.findById(id);
		if(ticketCategory.isPresent()) {
			result.put("Message", ticketCategory.get().toString());
		}else {
			result.put("Message", "ticketCategory ID is invalid");
		}
		return result;
	}
    
    public String createCategory(TicketCategory ticketCategory) {
        ticketCategoryRepository.save(ticketCategory);
        return "TicketCategory inserted successfully";
    }
    
    public String updateCategory(TicketCategoryId id, TicketCategory ticketCategory) {
        TicketCategory existingTicketCategory = ticketCategoryRepository.findById(id).orElse(null);
        if (existingTicketCategory != null) {
            existingTicketCategory.setTicket(ticketCategory.getTicket());
            existingTicketCategory.setProblemCategory(ticketCategory.getProblemCategory());
            ticketCategoryRepository.save(existingTicketCategory);
            return "TicketCategory updated successfully";
        }
        return null;
    }
	
	public Map<String, String> deleteCategory(TicketCategoryId id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<TicketCategory> ticketCategory = this.ticketCategoryRepository.findById(id);
		if(ticketCategory.isPresent()) {
			ticketCategoryRepository.deleteById(id);
			result.put("Message", "ticketCategory deleted successfully");
		}else {
			result.put("Message", "Failed to delete ticketCategory, TicketCategory ID is invalid");
		}
		return result;
	}

}