package com.example.demo.entity.board;

import lombok.Data;

@Data
public class ReplyVO {
	long reply_no;
	String reply_content;
	long board_no;
	
	public Reply convert() {
		return Reply.builder()
					.reply_no(reply_no)
					.reply_content(reply_content)
				.build();
	}
}
