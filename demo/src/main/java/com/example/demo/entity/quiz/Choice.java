package com.example.demo.entity.quiz;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.demo.converter.BooleanConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "choice_no", sequenceName = "choice_no", initialValue = 1, allocationSize = 1)
public class Choice {
	@Id
	@GeneratedValue(generator = "choice_seq")
	long choice_id;
	
	@Lob @Column(nullable = false)
	String text; // 지문
	
	// boolean은 DB에 저장하지 않고 1, 2와 같은 가벼운 값으로 바꿔서 처리
	//  - 자동 변환기를 만들어서 연결 설정(Converter)
	//  - check와 관련된 요소들을 거의 대부분 컨버터로 처리
	@Convert(converter = BooleanConverter.class)
	boolean answer; // 정답?
	
	@ManyToOne
	@JoinColumn(name = "question")
	@JsonBackReference // 안나옴(JSON)
	Question question;
}
