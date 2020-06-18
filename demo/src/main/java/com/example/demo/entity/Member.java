package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	String member_id;
	
	@Column(nullable = false, length = 20)
	String member_pw;
	
	@Column(nullable = false, length = 21)
	String member_nickname;
}
