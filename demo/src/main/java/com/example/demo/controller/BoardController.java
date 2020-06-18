package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.board.Board;
import com.example.demo.repository.BoardRepository;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardRepository boardRepo;
	
	@PostMapping("/")
	public Board write(@RequestBody Board board) {
		return boardRepo.save(board);
	}
	
	@GetMapping("/")
	public List<Board> list() {
		//return boardRepo.findAll();
		return boardRepo.getList();
	}
	
	
	
}
