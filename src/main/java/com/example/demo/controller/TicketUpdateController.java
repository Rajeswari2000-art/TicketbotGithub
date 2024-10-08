package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketUpdate;
import com.example.demo.service.TicketUpdateService;

@RestController
@RequestMapping("/updates")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketUpdateController {
	
	@Autowired
	TicketUpdateService ticketUpdateService;
	
	@GetMapping
    public List<TicketUpdate> getAllUpdates() {
        return ticketUpdateService.getAllUpdates();
    }

    @GetMapping("/{id}")
    public Map<String, String> getUpdateById(@PathVariable int updateId){
		return this.ticketUpdateService.getUpdateById(updateId);
	}

    @PostMapping
    public String createUpdate(@RequestBody TicketUpdate ticketUpdate) {
        return ticketUpdateService.createUpdate(ticketUpdate);
    }

    @PutMapping("/{id}")
    public String updateUpdate(@PathVariable Integer id, @RequestBody TicketUpdate ticketUpdate) {
        return ticketUpdateService.updateUpdate(id, ticketUpdate);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUpdate(@PathVariable int updateId){
		return this.ticketUpdateService.deleteUpdate(updateId);
	}

}
