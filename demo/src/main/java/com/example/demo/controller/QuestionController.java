package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.quiz.Question;
import com.example.demo.exception.ElementNotFoundException;
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
	
	@GetMapping("/question_no/{question_no}")
	public Question get(@PathVariable long question_no) {
		return questionRepo.findById(question_no).orElseThrow(ElementNotFoundException::new);
	}
	
	@PutMapping("/")
	public Question edit(@RequestBody Question question) {
		Question find = questionRepo.findById(question.getQuestion_no())
				.orElseThrow(ElementNotFoundException::new);
		find.setContent(question.getContent());
		find.setChoices(question.getChoices());
		return questionRepo.save(find);
	}
	
	@DeleteMapping("/")
	public void deleteAll() {
		questionRepo.deleteAll();
	}
	
	@DeleteMapping("/{question_no")
	public void delete(@PathVariable long question_no) {
		questionRepo.deleteById(question_no);
	}
	
}
