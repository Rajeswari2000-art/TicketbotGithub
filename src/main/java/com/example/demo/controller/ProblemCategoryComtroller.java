package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProblemCategory;
import com.example.demo.service.ProblemCategoryService;

@RestController
@RequestMapping("/problems")
public class ProblemCategoryComtroller {
	
	@Autowired
	ProblemCategoryService problemCategoryService;
	
	@GetMapping
    public List<ProblemCategory> getAllProblems() {
        return problemCategoryService.getAllProblems();
    }

    @GetMapping("/{id}")
    public Map<String, String> getProblemById(@PathVariable int id){
		return this.problemCategoryService.getProblemById(id);
	}

    @PostMapping
    public String createProblem(@RequestBody ProblemCategory problemCategory) {
        return problemCategoryService.createProblem(problemCategory);
    }

    @PutMapping("/{id}")
    public String updateProblem(@PathVariable Integer id, @RequestBody ProblemCategory problemCategory) {
        return problemCategoryService.updateProblem(id, problemCategory);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteProblem(@PathVariable int id){
		return this.problemCategoryService.deleteProblem(id);
	}
    
}
