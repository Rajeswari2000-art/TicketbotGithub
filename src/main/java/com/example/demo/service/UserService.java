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
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	public ResponseEntity<User> getUserById(int userId){
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with userId " + userId));
		return ResponseEntity.ok(user);
	}
	
	public String createUser(User user) {
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
        return "User inserted successfully";
    }

	public ResponseEntity<User> updateEmployee(int userId, User user) {
		User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with userId " + userId));
            existingUser.setLoginId(user.getLoginId());
            existingUser.setPasswordHash(user.getPasswordHash());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setUpdatedDate(LocalDateTime.now());
            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
    }
    
    public Map<String, String> deleteUser(int userId){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<User> user = this.userRepository.findById(userId);
		if(user.isPresent()) {
			userRepository.deleteById(userId);
			result.put("Message", "User deleted successfully");
		}else {
			result.put("Message", "Failed to delete user, User ID is invalid");
		}
		return result;
	}
    
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

}