package com.example.demo.entity.quiz;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "question_no", sequenceName = "question_no", initialValue = 1, allocationSize = 1)
public class Question {
	@Id
	@GeneratedValue(generator = "question_seq")
	long question_no;
	
	@Lob
	@Column(nullable = false)
	String content;
	
	// 관계 설정
	// - 1대 다 관계이면서 등록과 삭제가 동시에 이루어짐
	// - 연결 테이블을 따로 두지 않고 보기가 문제를 참조하는 구조로 DB를 구성하려면 mappedBy 옵션 사용
	@OneToMany(cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE},
			mappedBy = "question")
	// 문제는 무한 루프 (문제안에 보기, 보기안에 문제)
	// 문제는 무한 루프(json이나 toString의 경우)에 빠진다는 것
	@JsonManagedReference // 나옴(JSON)
	List<Choice> choices;
}
