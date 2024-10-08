package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProblemCategory;
import com.example.demo.repository.ProblemCategoryRepository;

@Service
public class ProblemCategoryService {
	
	@Autowired
	ProblemCategoryRepository problemCategoryRepository;
	
	public List<ProblemCategory> getAllProblems() {
        return problemCategoryRepository.findAll();
    }

	public Map<String, String> getProblemById(int id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<ProblemCategory> problemCategory = this.problemCategoryRepository.findById(id);
		if(problemCategory.isPresent()) {
			result.put("Message", problemCategory.get().toString());
		}else {
			result.put("Message", "ProblemCategory ID is invalid");
		}
		return result;
	}
	
    public String createProblem(ProblemCategory problemCategory) {
    	problemCategoryRepository.save(problemCategory);
        return "ProblemCategory inserted successfully";
    }
    
    public String updateProblem(Integer id, ProblemCategory problemCategory) {
        ProblemCategory existingProblem = problemCategoryRepository.findById(id).orElse(null);
        if (existingProblem != null) {
            existingProblem.setCategoryName(problemCategory.getCategoryName());
            problemCategoryRepository.save(existingProblem);
            return "ProblemCategory updated successfully";
        }
        return null;
    }
    
    public Map<String, String> deleteProblem(int id){
		HashMap<String, String> result = new HashMap<String, String>();
		Optional<ProblemCategory> problemCategory = this.problemCategoryRepository.findById(id);
		if(problemCategory.isPresent()) {
			problemCategoryRepository.deleteById(id);
			result.put("Message", "ProblemCategory deleted successfully");
		}else {
			result.put("Message", "Failed to delete problemCategory, ProblemCategory ID is invalid");
		}
		return result;
	}
    
}