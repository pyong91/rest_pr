package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // 테이블 생김
@SequenceGenerator( // 시퀀스 설정
		name="book_seq", 
		sequenceName = "book_seq",
		allocationSize = 1,
		initialValue = 1
)
public class Book {
	@Id // 기본키
	@GeneratedValue(generator = "book_seq") // 시퀀스 적용
	private int no;
	
	@Column(nullable = false, length = 60) //컬럼(설정)
	private String name;
	
	@Column(nullable = false, length = 60)
	private String publisher;
	
	@Column
	private int price;
	
	@CreationTimestamp // 생성시각
	private Timestamp ctime;
	
	@UpdateTimestamp // 수정시각
	private Timestamp utime;
}
