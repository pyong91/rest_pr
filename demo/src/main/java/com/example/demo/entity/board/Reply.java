package com.example.demo.entity.board;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "reply_seq", 
		sequenceName = "reply_seq", 
		initialValue = 1, 
		allocationSize = 1)
@ToString(exclude = {"board"})
public class Reply {
	
	@Id
	@GeneratedValue(generator = "reply_seq", strategy = GenerationType.AUTO)
	long reply_no;
	
	@Lob
	@Column(nullable = false)
	String reply_content;
	
	@CreationTimestamp
	Timestamp ctime;
	
	@UpdateTimestamp
	Timestamp utime;
	
	@ManyToOne
	@JoinColumn(name = "board") // 조인 컬럼의 이름
	@JsonBackReference // JSON만들 때 찍지 않는다
	Board board;
}
