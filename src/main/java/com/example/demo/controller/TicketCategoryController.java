package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketCategory;
import com.example.demo.model.TicketCategoryId;
import com.example.demo.service.TicketCategoryService;

@RestController
@RequestMapping("/categories")
public class TicketCategoryController {
	
	 @Autowired
	 TicketCategoryService ticketCategoryService;

	 @PostMapping
	 public String createCategory(@RequestBody TicketCategory ticketCategory) {
	    return ticketCategoryService.createCategory(ticketCategory);
	 }

	 @GetMapping
	 public ResponseEntity<List<TicketCategory>> getAllCategories() {
	    return ResponseEntity.ok(ticketCategoryService.findAllCategories());
	 }
	 
	 @GetMapping("/{id}")
	 public Map<String, String> getCategoryById(@PathVariable TicketCategoryId id){
		return this.ticketCategoryService.getCategoryById(id);
	 }
	 
	 @PutMapping("/{id}")
	 public String updateCategory(@PathVariable TicketCategoryId id, @RequestBody TicketCategory ticketCategory) {
	    return ticketCategoryService.updateCategory(id, ticketCategory);
	 }

	 @DeleteMapping("/{requestId}")
	 public Map<String, String> deleteCategory(@PathVariable TicketCategoryId id){
		return this.ticketCategoryService.deleteCategory(id);
	 }

}
