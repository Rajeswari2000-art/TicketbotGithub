package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId){
		return userService.getUserById(userId);
	}

	@PostMapping
	public String createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateEmployee(@PathVariable int userId, @RequestBody User user){
		return userService.updateEmployee(userId, user);
	}

	@DeleteMapping("/{userId}")
	public Map<String, String> deleteUser(@PathVariable int userId) {
		return this.userService.deleteUser(userId);
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody Map<String, String> credentials) {
	    String loginId = credentials.get("loginId");
	    String password = credentials.get("password");

	    User user = userService.findByLoginId(loginId);
	    if (user != null && user.getPasswordHash().equals(password)) {
	        return ResponseEntity.ok(user); // Return the User object
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

}