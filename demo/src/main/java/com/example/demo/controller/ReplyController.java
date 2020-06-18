package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.board.Reply;
import com.example.demo.entity.board.ReplyVO;
import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyRepository replyRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@PostMapping("/")
	public Reply write(@RequestBody ReplyVO replyVO) {
		Reply reply = replyVO.convert();
		reply.setBoard(boardRepo.findById(replyVO.getBoard_no()).orElseThrow(ElementNotFoundException::new));
		return replyRepo.save(reply);
	}
	
}
