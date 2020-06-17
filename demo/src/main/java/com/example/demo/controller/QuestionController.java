package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.quiz.Question;
import com.example.demo.repository.QuestionRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@PostMapping("/")
	public Question regist(@RequestBody Question question) {
		return questionRepo.save(question);
	}
	
	@GetMapping("/")
	public List<Question> list() {
		return questionRepo.findAll();
	}
	
}
